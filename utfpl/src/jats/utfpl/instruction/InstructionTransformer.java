package jats.utfpl.instruction;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.patcsps.type.PATType;
import jats.utfpl.patcsps.type.PATTypeBool;
import jats.utfpl.patcsps.type.PATTypeSingleton;
import jats.utfpl.tree.AppExp;
import jats.utfpl.tree.AtomExp;
import jats.utfpl.tree.Dec;
import jats.utfpl.tree.Exp;
import jats.utfpl.tree.FunDef;
import jats.utfpl.tree.FunGroup;
import jats.utfpl.tree.IdExp;
import jats.utfpl.tree.IfExp;
import jats.utfpl.tree.LamExp;
import jats.utfpl.tree.LetExp;
import jats.utfpl.tree.ProgramTree;
import jats.utfpl.tree.TreeVisitor;
import jats.utfpl.tree.TupleExp;
import jats.utfpl.tree.ValBind;
import jats.utfpl.tree.ValDef;
import jats.utfpl.tree.VarArrayDef;
import jats.utfpl.tree.VarAssign;
import jats.utfpl.tree.VarDef;

/*
 * convention:
 *   for exp, 
 *   If TID is fed and is not TID.ANONY, the return must be that TID. And there will be a MoveIns.
 *   If TID is null, the return can be a new TID or an AtomValue. 
 *     And there is no need for extra MoveIns. And the caller may
 *     use the newly created TID.
 *   
 *   If TID is fed with TID.ANONY, the return should have none.
 *   
 *   If m_vpOut is TID.ANONY, then the caller knows that there is no return value.
 * 
 */

public class InstructionTransformer implements TreeVisitor {
    public static final String cSetArray = "set_array";
    public static final String cGetArray = "get_array";
    public static final String cSetAddr = "set_addr";
    
    public static final String cAllocMutex = "mutex_allocate";
    
    private List<UtfplInstruction> m_inslst;
    private TID m_tidIn;  // holder designated by caller
    private ValPrim m_vpOut;  // entity returned by the callee, may be a holder
    
    private List<GlobalEntity> m_gEntitis;
    
    
    public InstructionTransformer() {
        m_inslst = new ArrayList<UtfplInstruction>();
        m_tidIn = null;
        m_vpOut = null;
        m_gEntitis = new ArrayList<GlobalEntity>();
    }
    
    // The function cannot be called multiple times.
    public ProgramInstruction trans(ProgramTree node) {
        this.visit(node);
        return new ProgramInstruction(m_gEntitis, m_inslst, null);
    }

    private TID getTIDIn() {
        TID ret = m_tidIn;
        m_tidIn = null;
        return ret;
    }
    
    private void setTIDIn(TID in) {
        if (null == m_tidIn) {
            m_tidIn = in;
        } else {
            throw new Error("should not happen");
        }
    }

    @Override
    public Object visit(ProgramTree node) {
        for (Dec dec: node.m_decs) {
            dec.accept(this);
        }
        return m_inslst;
    }

    @Override
    public Object visit(ValDef node) {
        TID holder = getTIDIn();  // save
        
        if (node.m_id == null) {
            throw new Error("Check this");
        }
        
        if (node.hasName()) {  // named global value
            // commented out @ 11/06/2013: no global value
            // GlobalValue gv = new GlobalValue(node.m_id.m_tid);
            // m_gEntitis.add(gv);
            setTIDIn(node.m_id.m_tid); 
        } else {
            setTIDIn(TID.ANONY);
        }
        
        Object ret = node.m_exp.accept(this);  // The store instruction is created here.
        
        setTIDIn(holder); 
        return ret;  
    }

    // FunDef must be inside certain FunGroup
    @Override
    public Object visit(FunDef node) {
        TID name = node.m_id.m_tid;
        List<TID> paralst = new ArrayList<TID>();
        for (IdExp id: node.m_paralst) {
            paralst.add(id.m_tid);
        }
        
        // create new transformer
        InstructionTransformer bodyVisitor = new InstructionTransformer();
        TID ret = TID.createRetHolder("ret");
        bodyVisitor.setTIDIn(ret);
//        bodyVisitor.m_inslst.add(new VarDefIns(ret));
        
        @SuppressWarnings("unchecked")
        List<UtfplInstruction> body = (List<UtfplInstruction>)node.m_body.accept(bodyVisitor);
        
        InsFuncDef fn = new InsFuncDef(name, paralst, body, ret);
        return fn;
    }

    @Override
    public Object visit(AppExp node) {
        TID holder = getTIDIn();
        
        // has to be a function name
        if (node.m_fun instanceof IdExp) {
            TID funlab = ((IdExp)node.m_fun).m_tid;
            PATType retType = funlab.getFunReturnType();
            
            // principle:
            // Do not add extra Return instruction in the end of the instruction list.
            // The last instruction should convey information enough to know that
            // it's the last instruction.
            if (funlab.getID().equals(cSetArray)) {
                // set_array(local, global, index)
                Exp lv = node.m_explst.get(0);
                lv.accept(this);
                ValPrim localValue = m_vpOut;
                
                Exp gv = node.m_explst.get(1);
                TID globalVar = ((IdExp)gv).m_tid;
                
                Exp ind = node.m_explst.get(2);
                ind.accept(this);
                ValPrim localIndex = m_vpOut;
                
                // This is store. There is no return value.
                InsStoreArray storeArr = new InsStoreArray(localValue, globalVar, localIndex);
                m_inslst.add(storeArr);
                if (holder.isRet()) {
                    InsRet ret = new InsRet(TupleValue.cNone);
                    m_inslst.add(ret);
                } else if (holder == TID.ANONY) {
                    // do nothing
                } else {
                    throw new Error("wrong " + holder);
                }
            } else if (funlab.getID().equals(cGetArray)) {
                // holder = get_array(global, index)
                Exp gv = node.m_explst.get(0);
                TID globalVar = ((IdExp)gv).m_tid;
                
                Exp ind = node.m_explst.get(1);
                ind.accept(this);
                ValPrim localIndex = m_vpOut;
                
                if (null == holder) {
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsLoadArray loadArr = new InsLoadArray(globalVar, localIndex, localHolder);
                    m_inslst.add(loadArr);
                    m_vpOut = localHolder;  // need to return the name
                } else if (holder.isGlobal()) {
                    // holder must be a valid name.
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsLoadArray loadArr = new InsLoadArray(globalVar, localIndex, localHolder);
                    m_inslst.add(loadArr);
                    InsStore store = new InsStore(localHolder, holder);
                    m_inslst.add(store);
                } else if (holder.isRet()) {
                    InsLoadArray loadArr = new InsLoadArray(globalVar, localIndex, holder);
                    m_inslst.add(loadArr);
                } else if (TID.ANONY == holder) {
                        throw new Error("should not write in this way");
                } else {
                    InsLoadArray loadArr = new InsLoadArray(globalVar, localIndex, holder);
                    m_inslst.add(loadArr);
                    m_vpOut = holder;
                }
            } else if (funlab.getID().equals(cAllocMutex)) {
                // holder = alloc_mutex()
                if (null == holder) {
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsAllocMutex alloc = new InsAllocMutex(localHolder);
                    m_inslst.add(alloc);
                    m_vpOut = localHolder;  // need to return the name
                }
                if (holder.isGlobal()) {
                    // holder must be a valid name.
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsAllocMutex alloc = new InsAllocMutex(localHolder);
                    m_inslst.add(alloc);
                    InsStore store = new InsStore(localHolder, holder);
                    m_inslst.add(store);
                } else if (holder.isRet()) {
                    InsAllocMutex alloc = new InsAllocMutex(holder);
                    m_inslst.add(alloc);
                } else if (TID.ANONY == holder) {
                    throw new Error("should not write in this way");                    
                } else {
                    InsAllocMutex alloc = new InsAllocMutex(holder);
                    m_inslst.add(alloc);
                    m_vpOut = holder;
                }
//            } else if (other instructions) {
//                xxx
            } else {  // normal function call
                List<ValPrim> args = new ArrayList<ValPrim>();
                // I didn't call getTIDIn() here, which leaves the freedom to exp.accept().
                for (Exp exp: node.m_explst) {
                    exp.accept(this);
                    if (null == m_vpOut) {
                        throw new Error("eeeeeeeee");
                    }
                    args.add(m_vpOut);
                }
                
                if (null == holder) {
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsCall app = new InsCall(localHolder, (TID)funlab, args, false);
                    m_inslst.add(app);
                    m_vpOut = localHolder;
                } else if (holder.isGlobal()) {
                    // holder must be a valid name.
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsCall app = new InsCall(localHolder, (TID)funlab, args, false);
                    m_inslst.add(app);
                    InsStore store = new InsStore(localHolder, holder);
                    m_inslst.add(store);
                } else if (holder.isRet()){
                    InsCall app = new InsCall(holder, funlab, args, true);
                    m_inslst.add(app);
                } else if (holder == TID.ANONY) {
                    // don't care the result
                    InsCall app = new InsCall(holder, funlab, args, false);  // impossible to be the tail call
                    m_inslst.add(app);
                } else if (holder.isLocal()) {
                    InsCall app = new InsCall(holder, (TID)funlab, args, false);
                    m_inslst.add(app);
                    m_vpOut = holder;
                } else {
                    System.out.println("holder is " + holder);
                    throw new Error("not supported");
                }
            }
        } else {
            throw new Error("not supported");
        }
        
        return m_inslst;
    }

    @Override
    public Object visit(AtomExp node) {
        TID holder = getTIDIn();
        
        if (null == holder) {
            m_vpOut = new AtomValue(node.m_text);
        } else if (TID.ANONY == holder) {
        } else if (holder.isGlobal()) {
            InsStore store = new InsStore(new AtomValue(node.m_text), holder);
            m_inslst.add(store);
        } else if (holder.isRet()) {
            InsRet ret = new InsRet(new AtomValue(node.m_text));
            m_inslst.add(ret);
        } else {
            InsMove move = new InsMove(new AtomValue(node.m_text), holder);
            m_inslst.add(move);
            m_vpOut = holder;
        }

        return m_inslst;
        
    }

    @Override
    public Object visit(IdExp node) {
        TID holder = getTIDIn();
        
        if (null == holder) {
            if (node.m_tid.isGlobal()) {
                TID localHolder = TID.createLocalVar("temp", node.m_tid.getType());
                InsLoad load = new InsLoad(node.m_tid, localHolder);
                m_inslst.add(load);
                m_vpOut = localHolder;
            } else {
                m_vpOut = node.m_tid;
            }
        } else if (holder.isGlobal()) {
            if (node.m_tid.isGlobal()) {
                TID localHolder = TID.createLocalVar("temp", node.m_tid.getType());
                InsLoad load = new InsLoad(node.m_tid, localHolder);
                m_inslst.add(load);
                InsStore store = new InsStore(localHolder, holder);
                m_inslst.add(store);
            } else {
                InsStore store = new InsStore(node.m_tid, holder);
                m_inslst.add(store);
            }
        } else if (holder.isRet()) {
            if (node.m_tid.isGlobal()) {
                TID localHolder = TID.createLocalVar("temp", node.m_tid.getType());
                InsLoad load = new InsLoad(node.m_tid, localHolder);
                m_inslst.add(load);
                InsRet ret = new InsRet(localHolder);
                m_inslst.add(ret);
            } else {
                InsRet ret = new InsRet(node.m_tid);
                m_inslst.add(ret);
            }
        } else {
            if (node.m_tid.isGlobal()) {
                InsLoad load = new InsLoad(node.m_tid, holder);
                m_inslst.add(load);
            } else {
                InsMove move = new InsMove(node.m_tid, holder);
                m_inslst.add(move);
            }
        }
        
        return m_inslst;
    }

    @Override
    public Object visit(IfExp node) {
        TID holder = getTIDIn();
        if (null == holder) {
            holder = TID.createLocalVar("if", PATTypeSingleton.cUnknownType);
//            m_inslst.add(new VarDefIns(holder));
        }
        
        node.m_cond.accept(this);
        ValPrim vpCond = m_vpOut;  //
        if (vpCond instanceof TID) {
            ((TID)vpCond).updateType(PATTypeBool.cType);
        }
        
        InstructionTransformer tVisitor = new InstructionTransformer();
        tVisitor.setTIDIn(holder);
        @SuppressWarnings("unchecked")
        List<UtfplInstruction> tInsLst = (List<UtfplInstruction>)node.m_btrue.accept(tVisitor);  //
        
        InstructionTransformer fVisitor = new InstructionTransformer();
        fVisitor.setTIDIn(holder);
        @SuppressWarnings("unchecked")
        List<UtfplInstruction> fInsLst = (List<UtfplInstruction>)node.m_bfalse.accept(fVisitor);  //
        
        InsCond ins = new InsCond(holder, vpCond, tInsLst, fInsLst, null);
        m_inslst.add(ins);
        m_vpOut = holder;
        
        return m_inslst;
        
    }

    @Override
    public Object visit(LamExp node) {
    	throw new Error("lambda is not supported");
//        TID holder = getTIDIn();
//        if (null == holder) {
//            holder = TID.createUserFun("lam");
//        } else {
//            holder.updateType(new PATTypeFunc());
//        }
//        
//        List<TID> paralst = new ArrayList<TID>();
//        for (IdExp id: node.m_paralst) {
//            paralst.add(id.m_tid);
//        }
//        
//        InsTransformer bodyVisitor = new InsTransformer();
//        TID ret = TID.createRetHolder("ret");
//        bodyVisitor.setTIDIn(ret);
//        
//        @SuppressWarnings("unchecked")
//        List<UtfplInstruction> body = (List<UtfplInstruction>)node.m_body.accept(bodyVisitor);
//        
//        FuncDefIns ins = new FuncDefIns(holder, paralst, body, ret);
//        m_inslst.add(new FuncGroupIns(ins));  // turn lambda into group too
//        m_vpOut = holder;
//        return m_inslst;
    }

    @Override
    public Object visit(LetExp node) {
        TID holder = getTIDIn();
        if (null == holder) {
            holder = TID.createLocalVar("let", PATTypeSingleton.cUnknownType);
//            m_inslst.add(new VarDefIns(holder));
        }
        
        for (Dec dec: node.m_decs) {
            dec.accept(this);
        }
        
        setTIDIn(holder);
        return node.m_exp.accept(this);
        
    }

    @Override
    public Object visit(TupleExp node) {
        if (node != TupleExp.Void) {
            throw new Error("Only support empty tuple currently.");
        }
        
        TID holder = getTIDIn();
        if (null != holder && holder.isRet()) {
            InsRet ret = new InsRet(TupleValue.cNone);
            m_inslst.add(ret);
            m_vpOut = holder;
        } else {
            throw new Error("not supported");
        }
        
        return m_inslst;
    }

    @Override
    public Object visit(VarDef node) {
    	// type system guarantees that node.m_id != null
    	TID holder = getTIDIn();
        
        Object ret = null;
        if (null != node.m_exp) {
        	setTIDIn(node.m_id.m_tid);
        	ret = node.m_exp.accept(this);
        } else {
        	ret = m_inslst;
        }
        setTIDIn(holder);
        
        m_gEntitis.add(new GlobalVariable(node.m_id.m_tid));
        
        return ret;
    }

    @Override
    public Object visit(VarAssign node) {
        TID Holder = getTIDIn();  // save
        
        setTIDIn(node.m_id.m_tid);
        Object ret = node.m_exp.accept(this);
        
        setTIDIn(Holder);  // restore
        return ret;
    }

	@Override
    public Object visit(FunGroup node) {
		List<InsFuncDef> insLst = new ArrayList<InsFuncDef>();
	    for (FunDef fundef: node.m_funLst) {
	    	insLst.add((InsFuncDef)fundef.accept(this));
	    }
	    
	    InsFuncGroup nIns = new InsFuncGroup(insLst);
	    m_inslst.add(nIns);
	    return m_inslst;
    }

    @Override
    public Object visit(ValBind node) {
        TID Holder = getTIDIn();  // save
        
        setTIDIn(node.m_id.m_tid);
        Object ret = node.m_exp.accept(this);
        
        setTIDIn(Holder);  // restore
        return ret;
    }

    @Override
    public Object visit(VarArrayDef node) {
        m_gEntitis.add(new GlobalArray(node.m_id.m_tid, node.m_size));
        return null;
    }



}