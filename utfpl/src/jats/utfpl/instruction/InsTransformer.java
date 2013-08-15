package jats.utfpl.instruction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jats.utfpl.tree.AppExp;
import jats.utfpl.tree.AtomExp;
import jats.utfpl.tree.Dec;
import jats.utfpl.tree.Exp;
import jats.utfpl.tree.FunDef;
import jats.utfpl.tree.IdExp;
import jats.utfpl.tree.IfExp;
import jats.utfpl.tree.LamExp;
import jats.utfpl.tree.LetExp;
import jats.utfpl.tree.Program;
import jats.utfpl.tree.TID;
import jats.utfpl.tree.TreeVisitor;
import jats.utfpl.tree.TupleExp;
import jats.utfpl.tree.ValDef;
import jats.utfpl.tree.VarAssign;
import jats.utfpl.tree.VarDef;

/*
 * convention:
 *   for exp, 
 *   If TID is fed, the return must be that TID. And there will be a MoveIns.
 *   If TID is not fed, the return can be a new TID or an AtomValue.
 * 
 */

public class InsTransformer implements TreeVisitor {
    private List<UtfplInstruction> m_inslst;
    private TID m_tidIn;  // holder designated by caller
    private ValPrim m_vpOut;  // entity returned by the callee, may be a holder
    
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
    
    public InsTransformer() {
        m_inslst = new ArrayList<UtfplInstruction>();
        m_tidIn = null;
        m_vpOut = null;
    }

    @Override
    public Object visit(Program node) {
        for (Dec dec: node.m_decs) {
            dec.accept(this);
        }
        return m_inslst;
    }

    @Override
    public Object visit(ValDef node) {
        TID holder = getTIDIn();
        
        if (node.m_id != null) {
            setTIDIn(node.m_id.m_tid);
        } else {
            setTIDIn(TID.ANONY);
        }
        
        Object ret = node.m_exp.accept(this);  // The move instruction is created here.
        setTIDIn(holder); 
        return ret;  
    }

    @Override
    public Object visit(FunDef node) {
        TID name = node.m_id.m_tid;
        List<TID> paralst = new ArrayList<TID>();
        for (IdExp id: node.m_paralst) {
            paralst.add(id.m_tid);
        }
        
        // create new transformer
        InsTransformer bodyVisitor = new InsTransformer();
        TID ret = TID.createLocalVar("ret");
        bodyVisitor.setTIDIn(ret);
//        bodyVisitor.m_inslst.add(new VarDefIns(ret));
        
        @SuppressWarnings("unchecked")
        List<UtfplInstruction> body = (List<UtfplInstruction>)node.m_body.accept(bodyVisitor);
        
        FuncDefIns fn = new FuncDefIns(name, paralst, body, ret);
        m_inslst.add(fn);
        return m_inslst;
    }

    @Override
    public Object visit(AppExp node) {
        TID holder = getTIDIn();
        if (null == holder) {
            holder = TID.createLocalVar("app");
            // m_inslst.add(new VarDefIns(holder));
        }
        
        node.m_fun.accept(this);
        ValPrim funlab = m_vpOut;
        
        List<ValPrim> args = new ArrayList<ValPrim>();
        
        for (Exp exp: node.m_explst) {
            exp.accept(this);
            args.add(m_vpOut);            
        }

        FuncCallIns app = new FuncCallIns(holder, (TID)funlab, args);
        m_inslst.add(app);
        m_vpOut = holder;
        return m_inslst;
    }

    @Override
    public Object visit(AtomExp node) {
        TID holder = getTIDIn();
        if (null != holder) {
            MoveIns ins = new MoveIns(holder, new AtomValue(node.m_text));
            m_inslst.add(ins);
            m_vpOut = holder;
        } else {
            m_vpOut = new AtomValue(node.m_text);
        }
        
        return m_inslst;
        
    }

    @Override
    public Object visit(IdExp node) {
        TID holder = getTIDIn();
        if (null != holder) {
            MoveIns ins = new MoveIns(holder, node.m_tid);
            m_inslst.add(ins);
            m_vpOut = holder;
        } else {
            m_vpOut = node.m_tid;
        }
        
        return m_inslst;
    }

    @Override
    public Object visit(IfExp node) {
        TID holder = getTIDIn();
        if (null == holder) {
            holder = TID.createLocalVar("if");
//            m_inslst.add(new VarDefIns(holder));
        }
        
        node.m_cond.accept(this);
        ValPrim vpCond = m_vpOut;  //
        
        InsTransformer tVisitor = new InsTransformer();
        tVisitor.setTIDIn(holder);
        @SuppressWarnings("unchecked")
        List<UtfplInstruction> tInsLst = (List<UtfplInstruction>)node.m_btrue.accept(tVisitor);  //
        
        InsTransformer fVisitor = new InsTransformer();
        fVisitor.setTIDIn(holder);
        @SuppressWarnings("unchecked")
        List<UtfplInstruction> fInsLst = (List<UtfplInstruction>)node.m_bfalse.accept(fVisitor);  //
        
        CondIns ins = new CondIns(holder, vpCond, tInsLst, fInsLst);
        m_inslst.add(ins);
        m_vpOut = holder;
        
        return m_inslst;
        
    }

    @Override
    public Object visit(LamExp node) {
        TID holder = getTIDIn();
        if (null == holder) {
            holder = TID.createUserFun("lam");
        }
        
        List<TID> paralst = new ArrayList<TID>();
        for (IdExp id: node.m_paralst) {
            paralst.add(id.m_tid);
        }
        
        InsTransformer bodyVisitor = new InsTransformer();
        TID ret = TID.createLocalVar("ret");
        bodyVisitor.setTIDIn(ret);
//        bodyVisitor.m_inslst.add(new VarDefIns(ret));
        
        @SuppressWarnings("unchecked")
        List<UtfplInstruction> body = (List<UtfplInstruction>)node.m_body.accept(bodyVisitor);
        
        FuncDefIns ins = new FuncDefIns(holder, paralst, body, ret);
        m_inslst.add(ins);
        m_vpOut = holder;        
        return m_inslst;
    }

    @Override
    public Object visit(LetExp node) {
        TID holder = getTIDIn();
        if (null == holder) {
            holder = TID.createLocalVar("let");
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
        throw new Error("not supported");
    }

    @Override
    public Object visit(VarDef node) {
//        m_inslst.add(new VarDefIns(node.m_id.m_tid));
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
        return ret;
    }

    @Override
    public Object visit(VarAssign node) {
        TID Holder = getTIDIn();
        setTIDIn(node.m_id.m_tid);
        Object ret = node.m_exp.accept(this);
        setTIDIn(Holder);
        return ret;
    }

}
