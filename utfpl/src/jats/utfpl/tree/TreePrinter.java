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
    
    public String print(ProgramTree prog) {
        Object out = prog.accept(this);
        if (out instanceof ST) {
            return ((ST)out).render();
        } else {
            throw new Error("shall not happen");
        }
        
    }

    @Override
    public Object visit(ExpApp node) {
        ST st = m_stg.getInstanceOf("app_exp_st");
        st.add("fun", node.m_fun.accept(this));
        for (IExp exp: node.m_explst) {
           st.add("args", exp.accept(this));
        }
        return st;        
    }

    @Override
    public Object visit(ExpAtom node) {
        ST st = m_stg.getInstanceOf("atom_exp_st");
        st.add("v", node.m_text);
        return st;        
    }

    @Override
    public Object visit(DecFunDef node) {
        // fun_def_st(fname, paralst, body) ::= <<
        ST st = m_stg.getInstanceOf("fun_def_st");
        st.add("fname", node.m_id.accept(this));
        
        for (ExpId id: node.m_paralst) {
            st.add("paralst", id.accept(this));
        }
        
        st.add("body", node.m_body.accept(this));
        return st;
    }

    @Override
    public Object visit(ExpId node) {
        // id_exp_st(id) ::= <<
        ST st = m_stg.getInstanceOf("id_exp_st");
        if (node.m_tid == null) {
            st.add("id", node.m_sid);
            // System.err.println("error, id is " + node.m_id);
        } else {
            st.add("id", node.m_tid.toString());
        }
       
        return st;
    }

    @Override
    public Object visit(ExpIf node) {
        // if_exp_st(cond, btrue, bfalse) ::= <<
        ST st = m_stg.getInstanceOf("if_exp_st");
        st.add("cond", node.m_cond.accept(this));
        st.add("btrue", node.m_btrue.accept(this));
        st.add("bfalse", node.m_bfalse.accept(this));
        return st;
    }

    @Override
    public Object visit(ExpLam node) {
        // lam_exp_st(paralst, body) ::= <<
        ST st = m_stg.getInstanceOf("lam_exp_st");
        for (ExpId id: node.m_paralst) {
            st.add("paralst", id.accept(this));
        }
        st.add("body", node.m_body.accept(this));
        
        return st;
    }

    @Override
    public Object visit(ExpLet node) {
        // let_exp_st(decs, exp) ::= <<
        ST st = m_stg.getInstanceOf("let_exp_st");
        for (IDec dec: node.m_decs) {
            st.add("decs", dec.accept(this));
        }
        st.add("exp", node.m_exp.accept(this));
        
        return st;
    }

    @Override
    public Object visit(DecValDef node) {
        ST st = m_stg.getInstanceOf("val_def_st");
        st.add("id", node.m_id.accept(this));
        st.add("exp", node.m_exp.accept(this));
        return st;
    }

    @Override
    public Object visit(ProgramTree node) {
        // program_st(decs) ::= << 
        ST st = m_stg.getInstanceOf("program_st");
        for (IDec dec: node.m_decs) {
            st.add("decs", dec.accept(this));
        }
        return st;
    }

    @Override
    public Object visit(ExpTuple node) {
        ST st = null;
        if (node.isSingle()) {
            st = m_stg.getInstanceOf("tuple_exp_single_st");
            st.add("exp", node.m_components.get(0).accept(this));
        } else {
            st = m_stg.getInstanceOf("tuple_exp_st");
            if (!node.isVoid()) {
                List<Object> explst = new ArrayList<Object>();
                for (IExp exp : node.m_components) {
                    explst.add(exp.accept(this));
                }
                st.add("explst", explst);
            }
        }
        return st;
    }

    @Override
    public Object visit(DecVarDef node) {
        ST st = m_stg.getInstanceOf("var_def_st");
        st.add("id", node.m_id.accept(this));
        if (null != node.m_exp) {
            st.add("exp", node.m_exp.accept(this));
        }
        
        return st;
    }

    @Override
    public Object visit(DecVarAssign node) {
        ST st = m_stg.getInstanceOf("var_assign_st");
        st.add("id", node.m_id.accept(this));
        st.add("exp", node.m_exp.accept(this));
        return st;
    }

	@Override
    public Object visit(DecFunGroup node) {
	    ST st = m_stg.getInstanceOf("fun_group_st");
	    for (DecFunDef fundef: node.m_funLst) {
	    	st.add("fun_lst", fundef.accept(this));
	    }
	    return st;
	    
    }

    @Override
    public Object visit(DecVarArrayDef node) {
        ST st = m_stg.getInstanceOf("var_array_def_st");
        st.add("id", node.m_id.accept(this));
        st.add("size", node.m_size);
        return st;
    }

    @Override
    public Object visit(DecValBind node) {
        // val_bind_st(id, exp) ::= <<
        ST st = m_stg.getInstanceOf("val_bind_st");
        if (node.m_id != null) {
            st.add("id", node.m_id.accept(this));
        }
        st.add("exp", node.m_exp.accept(this));
        return st;
    }

}


