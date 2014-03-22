package jats.utfpl.instruction;

import java.util.ArrayList;
import java.util.List;

import jats.utfpl.ccomp.CCompUtils;
import jats.utfpl.patcsps.type.PATType;
import jats.utfpl.patcsps.type.PATTypeBool;
import jats.utfpl.patcsps.type.PATTypeSingleton;
import jats.utfpl.tree.DecExtCode;
import jats.utfpl.tree.DecFunDec;
import jats.utfpl.tree.DecFunImpl;
import jats.utfpl.tree.ExpApp;
import jats.utfpl.tree.ExpAtom;
import jats.utfpl.tree.IDec;
import jats.utfpl.tree.IExp;
import jats.utfpl.tree.DecFunDef;
import jats.utfpl.tree.DecFunGroup;
import jats.utfpl.tree.ExpId;
import jats.utfpl.tree.ExpIf;
import jats.utfpl.tree.ExpLam;
import jats.utfpl.tree.ExpLet;
import jats.utfpl.tree.ProgramTree;
import jats.utfpl.tree.TreeVisitor;
import jats.utfpl.tree.ExpTuple;
import jats.utfpl.tree.DecValBind;
import jats.utfpl.tree.DecValDef;
import jats.utfpl.tree.DecVarArrayDef;
import jats.utfpl.tree.DecVarAssign;
import jats.utfpl.tree.DecVarDef;

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
    
    private List<UtfplInstruction> m_inslst;
    private TID m_tidIn;  // holder designated by caller
    private ValPrim m_vpOut;  // entity returned by the callee, may be a holder
    
    private List<GlobalEntity> m_gEntitis;
    private List<GlobalExtCode> m_extCodeLst;
    
    
    public InstructionTransformer() {
        m_inslst = new ArrayList<UtfplInstruction>();
        m_tidIn = null;
        m_vpOut = null;
        m_gEntitis = new ArrayList<GlobalEntity>();
        m_extCodeLst = new ArrayList<GlobalExtCode>();
    }
    
    // The function cannot be called multiple times.
    public ProgramInstruction trans(ProgramTree node) {
        this.visit(node);
        return new ProgramInstruction(m_gEntitis, m_inslst, null, m_extCodeLst);
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
        for (IDec dec: node.m_decs) {
            dec.accept(this);
        }
        return m_inslst;
    }

    @Override
    public Object visit(DecValDef node) {
        TID holder = getTIDIn();  // save
        
        if (node.m_id == null) {
            throw new Error("Check this");
        }
        
        if (node.hasName()) {  // named global value
             GlobalValue gv = new GlobalValue(node.m_id.m_tid);
             m_gEntitis.add(gv);
            setTIDIn(node.m_id.m_tid); 
        } else {
            setTIDIn(TID.ANONY);
        }
        
        Object ret = node.m_exp.accept(this);  // The store instruction is created here.
        
        setTIDIn(holder); 
        return ret;  
    }

    @Override
    public Object visit(ExpApp node) {
        TID holder = getTIDIn();
        
        // has to be a function name
        if (node.m_fun instanceof ExpId) {
            TID funlab = ((ExpId)node.m_fun).m_tid;
            PATType retType = funlab.getFunReturnType();
            
            // principle:
            // Add extra Return instruction in the end of the instruction list if
            // the last instruction doesn't contain information of holder.
            if (funlab.getID().equals(CCompUtils.cSysGarrUpdate)) {
                // val () = sys_garr_update (garr, index, value (local))

                IExp gv = node.m_explst.get(0);
                TID globalVar = ((ExpId)gv).m_tid;
                
                IExp ind = node.m_explst.get(1);
                ind.accept(this);
                ValPrim localIndex = m_vpOut;
                
                IExp lv = node.m_explst.get(2);
                lv.accept(this);
                ValPrim localValue = m_vpOut;
                
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
            } else if (funlab.getID().equals(CCompUtils.cSysGarrGet)) {
                // holder = sys_array_get(global, index)
                IExp gv = node.m_explst.get(0);
                TID globalVar = ((ExpId)gv).m_tid;
                
                IExp ind = node.m_explst.get(1);
                ind.accept(this);
                ValPrim localIndex = m_vpOut;
                
                if (null == holder) {
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsLoadArray loadArr = new InsLoadArray(globalVar, localIndex, localHolder);
                    m_inslst.add(loadArr);
                    m_vpOut = localHolder;  // need to return the name
                } else if (holder.isGlobalVariable()) {
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
            } else if (funlab.getID().equals(CCompUtils.cSysMutexAlloc)) {
                // holder = sys_mutex_allocate ()
                if (null == holder) {
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsMutexAlloc alloc = new InsMutexAlloc(localHolder);
                    m_inslst.add(alloc);
                    m_vpOut = localHolder;  // need to return the name
                }
                if (holder.isGlobalVariable()) {
                    // holder must be a valid name.
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsMutexAlloc alloc = new InsMutexAlloc(localHolder);
                    m_inslst.add(alloc);
                    InsStore store = new InsStore(localHolder, holder);
                    m_inslst.add(store);
                } else if (holder.isRet()) {
                    InsMutexAlloc alloc = new InsMutexAlloc(holder);
                    m_inslst.add(alloc);
                } else if (TID.ANONY == holder) {
                    throw new Error("should not write in this way");                    
                } else {
                    InsMutexAlloc alloc = new InsMutexAlloc(holder);
                    m_inslst.add(alloc);
                    m_vpOut = holder;
                }
            } else if (funlab.getID().equals(CCompUtils.cSysThreadCreate)) {
                // val () = sys_thread_create (tid, funlab, args)
                IExp eTid = node.m_explst.get(0);
                eTid.accept(this);
                ValPrim vpTid = m_vpOut;
                
                IExp eFunLab = node.m_explst.get(1);
                TID funLab = ((ExpId)eFunLab).m_tid;
                
                IExp eArgs = node.m_explst.get(2);
                eArgs.accept(this);
                ValPrim args = m_vpOut;
                
                // This is sys_thread_create. There is no return value.
                InsThreadCreate createThread = new InsThreadCreate(vpTid, funLab, args);
                m_inslst.add(createThread);
                if (holder.isRet()) {
                    InsRet ret = new InsRet(TupleValue.cNone);
                    m_inslst.add(ret);
                } else if (holder == TID.ANONY) {
                    // do nothing
                } else {
                    throw new Error("wrong " + holder);
                }
            } else if (funlab.getID().equals(CCompUtils.cSysMutexRelease)) {
                // sys_mutex_release (m)
                IExp mv = node.m_explst.get(0);
                mv.accept(this);
                ValPrim mutex = m_vpOut;

                // This is release. There is no return value.
                InsMutexRelease mutexRelease = new InsMutexRelease(mutex);
                m_inslst.add(mutexRelease);
                if (holder.isRet()) {
                    InsRet ret = new InsRet(TupleValue.cNone);
                    m_inslst.add(ret);
                } else if (holder == TID.ANONY) {
                    // do nothing
                } else {
                    throw new Error("wrong " + holder);
                }
            } else if (funlab.getID().equals(CCompUtils.cSysCondAlloc)) {
                // holder = sys_cond_allocate ()
                if (null == holder) {
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsCondAlloc alloc = new InsCondAlloc(localHolder);
                    m_inslst.add(alloc);
                    m_vpOut = localHolder;  // need to return the name
                }
                if (holder.isGlobalVariable()) {
                    // holder must be a valid name.
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsCondAlloc alloc = new InsCondAlloc(localHolder);
                    m_inslst.add(alloc);
                    InsStore store = new InsStore(localHolder, holder);
                    m_inslst.add(store);
                } else if (holder.isRet()) {
                	InsCondAlloc alloc = new InsCondAlloc(holder);
                    m_inslst.add(alloc);
                } else if (TID.ANONY == holder) {
                    throw new Error("should not write in this way");                    
                } else {
                	InsCondAlloc alloc = new InsCondAlloc(holder);
                    m_inslst.add(alloc);
                    m_vpOut = holder;
                }
            } else if (funlab.getID().equals(CCompUtils.cSysCondRelease)) {
                // sys_cond_release (m)
                IExp mv = node.m_explst.get(0);
                mv.accept(this);
                ValPrim cond = m_vpOut;

                // This is release. There is no return value.
                InsCondRelease condRelease = new InsCondRelease(cond);
                m_inslst.add(condRelease);
                if (holder.isRet()) {
                    InsRet ret = new InsRet(TupleValue.cNone);
                    m_inslst.add(ret);
                } else if (holder == TID.ANONY) {
                    // do nothing
                } else {
                    throw new Error("wrong " + holder);
                }
            } else if (funlab.getID().equals(CCompUtils.cSysMCSetInt)) {
                // prval () = mc_set_int (g1, local)

                IExp gv = node.m_explst.get(0);
                TID globalVar = ((ExpId)gv).m_tid;

                IExp lv = node.m_explst.get(1);
                lv.accept(this);
                ValPrim localValue = m_vpOut;
                
                // This is store. There is no return value.
                InsStore mcSetInt = new InsStore(localValue, globalVar);
                m_inslst.add(mcSetInt);
                if (holder.isRet()) {
//                    InsRet ret = new InsRet(TupleValue.cNone);
//                    m_inslst.add(ret);
                    throw new Error("wrong " + holder);
                } else if (holder == TID.ANONY) {
                    // do nothing
                } else {
                    throw new Error("wrong " + holder);
                }
            } else if (funlab.getID().equals(CCompUtils.cSysMCGetInt)) {
                // prval (pf | mc_x) = mc_get_int (g1)
                if (null == holder) {
//                    TID localHolder = TID.createLocalVar("temp", retType);
//                    InsMutexAlloc alloc = new InsMutexAlloc(localHolder);
//                    m_inslst.add(alloc);
//                    m_vpOut = localHolder;  // need to return the name
                    throw new Error("check this");
                }
                if (holder.isGlobalVariable()) {
//                    // holder must be a valid name.
//                    TID localHolder = TID.createLocalVar("temp", retType);
//                    InsMutexAlloc alloc = new InsMutexAlloc(localHolder);
//                    m_inslst.add(alloc);
//                    InsStore store = new InsStore(localHolder, holder);
//                    m_inslst.add(store);
                    throw new Error("check this");
                } else if (holder.isRet()) {
                    InsMutexAlloc alloc = new InsMutexAlloc(holder);
                    m_inslst.add(alloc);
                } else if (TID.ANONY == holder) {
                    throw new Error("should not write in this way");                    
                } else {
                    IExp gv = node.m_explst.get(0);
                    TID globalVar = ((ExpId)gv).m_tid;
                    InsLoad insLoad = new InsLoad(globalVar, holder);
                    m_inslst.add(insLoad);
                    m_vpOut = holder;
                }     
            } else if (funlab.getID().equals(CCompUtils.cSysMCAssert)) {
                //   prval () = mc_assert (xx > 6)
                IExp lv = node.m_explst.get(0);
                lv.accept(this);
                ValPrim localValue = m_vpOut;
                
                // This is store. There is no return value.
                InsMCAssert mcAssert = new InsMCAssert(localValue);
                m_inslst.add(mcAssert);
                if (holder.isRet()) {
//                    InsRet ret = new InsRet(TupleValue.cNone);
//                    m_inslst.add(ret);
                    throw new Error("wrong " + holder);
                } else if (holder == TID.ANONY) {
                    // do nothing
                } else {
                    throw new Error("wrong " + holder);
                }
//            } else if (other instructions) {
//                xxx
            } else {  // normal function call
                List<ValPrim> args = new ArrayList<ValPrim>();
                // I didn't call getTIDIn() here, which leaves the freedom to exp.accept().
                for (IExp exp: node.m_explst) {
                    exp.accept(this);
                    if (null == m_vpOut) {
                        throw new Error("check this");
                    }
                    args.add(m_vpOut);
                }
                
                if (null == holder) {
                    TID localHolder = TID.createLocalVar("temp", retType);
                    InsCall app = new InsCall(localHolder, (TID)funlab, args, false);
                    m_inslst.add(app);
                    m_vpOut = localHolder;
                } else if (holder.isGlobalVariable()) {
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
                } else if (holder.isLocal() || holder.isGlobalValue()) {
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
    public Object visit(ExpAtom node) {
        TID holder = getTIDIn();
        
        if (null == holder) {
            m_vpOut = new AtomValue(node.m_text);
        } else if (TID.ANONY == holder) {
        } else if (holder.isGlobalVariable()) {
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
    public Object visit(ExpId node) {
        TID holder = getTIDIn();
        
        if (null == holder) {
            if (node.m_tid.isGlobalVariable()) {
                TID localHolder = TID.createLocalVar("temp", node.m_tid.getType());
                InsLoad load = new InsLoad(node.m_tid, localHolder);
                m_inslst.add(load);
                m_vpOut = localHolder;
            } else {
                m_vpOut = node.m_tid;
            }
        } else if (holder.isGlobalVariable()) {
            if (node.m_tid.isGlobalVariable()) {
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
            if (node.m_tid.isGlobalVariable()) {
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
            if (node.m_tid.isGlobalVariable()) {
                InsLoad load = new InsLoad(node.m_tid, holder);
                m_inslst.add(load);
            } else {
                InsMove move = new InsMove(node.m_tid, holder);
                m_inslst.add(move);
            }
        }
        
        return m_inslst;
    }


    @SuppressWarnings("unchecked")
    @Override
    public Object visit(ExpIf node) {
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

        List<UtfplInstruction> tInsLst = (List<UtfplInstruction>)node.m_btrue.accept(tVisitor);  //
        
        InstructionTransformer fVisitor = new InstructionTransformer();
        fVisitor.setTIDIn(holder);

        List<UtfplInstruction> fInsLst = null;
        if (null != node.m_bfalse) {
        	fInsLst = (List<UtfplInstruction>)node.m_bfalse.accept(fVisitor);  //
        } else {
        	fInsLst = new ArrayList<UtfplInstruction>();
        }

        InsCond ins = new InsCond(holder, vpCond, tInsLst, fInsLst, null);
        m_inslst.add(ins);
        m_vpOut = holder;
        
        return m_inslst;
        
    }

    @Override
    public Object visit(ExpLam node) {
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
    public Object visit(ExpLet node) {
        TID holder = getTIDIn();
        if (null == holder) {
            holder = TID.createLocalVar("let", PATTypeSingleton.cUnknownType);
//            m_inslst.add(new VarDefIns(holder));
        }
        
        for (IDec dec: node.m_decs) {
            dec.accept(this);
        }
        
        setTIDIn(holder);
        return node.m_exp.accept(this);
        
    }

    @Override
    public Object visit(ExpTuple node) {
        if (!node.isVoid()) {
            throw new Error("Only support empty tuple currently.");
        }
        
        TID holder = getTIDIn();
        if (null != holder && holder.isRet()) {
            InsRet ret = new InsRet(TupleValue.cNone);
            m_inslst.add(ret);
            m_vpOut = holder;
        } else {
            throw new Error("empty tuple as right value is not supported");
        }
        
        return m_inslst;
    }

    @Override
    public Object visit(DecVarDef node) {
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
    public Object visit(DecVarAssign node) {
        TID Holder = getTIDIn();  // save
        
        setTIDIn(node.m_id.m_tid);
        Object ret = node.m_exp.accept(this);
        
        setTIDIn(Holder);  // restore
        return ret;
    }

	@Override
    public Object visit(DecFunGroup node) {
		List<InsFuncDef> insLst = new ArrayList<InsFuncDef>();
	    for (DecFunDef fundef: node.m_funLst) {
	    	insLst.add((InsFuncDef)fundef.accept(this));
	    }
	    
	    InsFuncGroup nIns = new InsFuncGroup(insLst);
	    m_inslst.add(nIns);
	    return m_inslst;
    }
	

    // FunDef must be inside certain FunGroup
    @Override
    public Object visit(DecFunDef node) {
        TID name = node.m_id.m_tid;
        List<TID> paralst = new ArrayList<TID>();
        for (ExpId id: node.m_paralst) {
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
    public Object visit(DecValBind node) {
        TID Holder = getTIDIn();  // save
        
        setTIDIn(node.m_id.m_tid);
        Object ret = node.m_exp.accept(this);
        
        setTIDIn(Holder);  // restore
        return ret;
    }

    @Override
    public Object visit(DecVarArrayDef node) {
        m_gEntitis.add(new GlobalArray(node.m_id.m_tid, node.m_size));
        return null;
    }

    @Override
    public Object visit(DecExtCode node) {
        m_extCodeLst.add(new GlobalExtCode(node.m_content));
        return null;
    }

    @Override
    public Object visit(DecFunDec decFunDec) {
        // do nothing
        return null;
    }

    @Override
    public Object visit(DecFunImpl node) {
        
        // Create a InsFuncGroup with only one function inside it.
        // This implies that the function shall not be a closure. Otherwise, it would
        // cause trouble to closure removal by lifting escaped values onto arguments.
        
        List<InsFuncDef> insLst = new ArrayList<InsFuncDef>();

        TID name = node.m_id.m_tid;
        List<TID> paralst = new ArrayList<TID>();
        for (ExpId id: node.m_paralst) {
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
        
        insLst.add(fn);
        
        InsFuncGroup nIns = new InsFuncGroup(insLst);
        m_inslst.add(nIns);
        return m_inslst;
        

    }



}
