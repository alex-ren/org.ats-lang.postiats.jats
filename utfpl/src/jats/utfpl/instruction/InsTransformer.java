package jats.utfpl.instruction;

import java.util.ArrayList;
import java.util.List;

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
import jats.utfpl.tree.ValDef;

/*
 * convention:
 *   for exp, 
 *   If TID is fed, the return must be that TID. And there will be a MoveIns.
 *   If TID is not fed, the return can be a new TID or an AtomValue.
 * 
 */

public class InsTransformer implements TreeVisitor {
    private List<UtfplInstruction> m_inslst;
    private TID m_tidIn;
    private ValPrim m_vpOut;
    
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
        m_inslst.add(new VarDefIns(node.m_id.m_tid));
        setTIDIn(node.m_id.m_tid);
        return node.m_exp.accept(this);
    }

    @Override
    public Object visit(FunDef node) {
        TID name = node.m_id.m_tid;
        List<TID> paralst = new ArrayList<TID>();
        for (IdExp id: node.m_paralst) {
            paralst.add(id.m_tid);
        }
        
        InsTransformer bodyVisitor = new InsTransformer();
        TID ret = TID.create("ret");
        ret.setUsed();
        bodyVisitor.setTIDIn(ret);
        bodyVisitor.m_inslst.add(new VarDefIns(ret));
        
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
            holder = TID.create("app");
            m_inslst.add(new VarDefIns(holder));
        }
        holder.setUsed();
        
        node.m_fun.accept(this);
        ValPrim funlab = m_vpOut;
        
        List<ValPrim> args = new ArrayList<ValPrim>();
        
        for (Exp exp: node.m_explst) {
            exp.accept(this);
            args.add(m_vpOut);            
        }

        FuncCallIns app = new FuncCallIns(holder, funlab, args);
        m_inslst.add(app);
        m_vpOut = holder;
        return m_inslst;
    }

    @Override
    public Object visit(AtomExp node) {
        TID holder = getTIDIn();
        if (null != holder) {
            holder.setUsed();
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
            holder.setUsed();
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
            holder = TID.create("if");
            m_inslst.add(new VarDefIns(holder));
        }
        holder.setUsed();
        
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
            holder = TID.create("lam");
        }
        holder.setUsed();
        
        List<TID> paralst = new ArrayList<TID>();
        for (IdExp id: node.m_paralst) {
            paralst.add(id.m_tid);
        }
        
        InsTransformer bodyVisitor = new InsTransformer();
        TID ret = TID.create("ret");
        ret.setUsed();
        bodyVisitor.setTIDIn(ret);
        bodyVisitor.m_inslst.add(new VarDefIns(ret));
        
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
            holder = TID.create("let");
            m_inslst.add(new VarDefIns(holder));
        }
        
        for (Dec dec: node.m_decs) {
            dec.accept(this);
        }
        
        setTIDIn(holder);
        return node.m_exp.accept(this);
        
    }

}
