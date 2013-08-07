package jats.utfpl.tree;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class TreePrinter implements TreeVisitor {
    
    private STGroup m_stg;
    
    public TreePrinter() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/tree/tree.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');

    }
    
    public String print(Program prog) {
        Object out = prog.accept(this);
        if (out instanceof ST) {
            return ((ST)out).render();
        } else {
            throw new Error("shall not happen");
        }
        
    }

    @Override
    public Object visit(AppExp node) {
        ST st = m_stg.getInstanceOf("app_exp_st");
        st.add("fun", node.m_fun.accept(this));
        for (Exp exp: node.m_explst) {
           st.add("args", exp.accept(this));
        }
        return st;        
    }

    @Override
    public Object visit(AtomExp node) {
        ST st = m_stg.getInstanceOf("atom_exp_st");
        st.add("v", node.m_text);
        return st;        
    }

    @Override
    public Object visit(FunDef node) {
        // fun_def_st(fname, paralst, body) ::= <<
        ST st = m_stg.getInstanceOf("fun_def_st");
        st.add("fname", node.m_id.accept(this));
        
        for (IdExp id: node.m_paralst) {
            st.add("paralst", id.accept(this));
        }
        
        st.add("body", node.m_body.accept(this));
        return st;
    }

    @Override
    public Object visit(IdExp node) {
        // id_exp_st(id) ::= <<
        ST st = m_stg.getInstanceOf("id_exp_st");
        if (node.m_tid == null) {
            System.err.println("error, id is " + node.m_id);
        }
        st.add("id", node.m_tid.toString());
//        st.add("id", node.m_id);
        
        return st;
    }

    @Override
    public Object visit(IfExp node) {
        // if_exp_st(cond, btrue, bfalse) ::= <<
        ST st = m_stg.getInstanceOf("if_exp_st");
        st.add("cond", node.m_cond.accept(this));
        st.add("btrue", node.m_btrue.accept(this));
        st.add("bfalse", node.m_bfalse.accept(this));
        return st;
    }

    @Override
    public Object visit(LamExp node) {
        // lam_exp_st(paralst, body) ::= <<
        ST st = m_stg.getInstanceOf("lam_exp_st");
        for (IdExp id: node.m_paralst) {
            st.add("paralst", id.accept(this));
        }
        st.add("body", node.m_body.accept(this));
        
        return st;
    }

    @Override
    public Object visit(LetExp node) {
        // let_exp_st(decs, exp) ::= <<
        ST st = m_stg.getInstanceOf("let_exp_st");
        for (Dec dec: node.m_decs) {
            st.add("decs", dec.accept(this));
        }
        st.add("exp", node.m_exp.accept(this));
        
        return st;
    }

    @Override
    public Object visit(ValDef node) {
        // val_def_st(id, exp) ::= <<
        ST st = m_stg.getInstanceOf("val_def_st");
        st.add("id", node.m_id.accept(this));
        st.add("exp", node.m_exp.accept(this));
        return st;
    }

    @Override
    public Object visit(Program node) {
        // program_st(decs) ::= << 
        ST st = m_stg.getInstanceOf("program_st");
        for (Dec dec: node.m_decs) {
            st.add("decs", dec.accept(this));
        }
        return st;
    }

    @Override
    public Object visit(TupleExp node) {
        ST st = m_stg.getInstanceOf("tuple_exp_st");
        if (node != TupleExp.Void) {
            List<Object> explst = new ArrayList<Object>();
            for (Exp exp : node.m_components) {
                explst.add(exp.accept(this));
            }
            st.add("explst", explst);
        }
        return st;
        
    }

}