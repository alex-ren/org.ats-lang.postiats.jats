package jats.utfpl.instruction;


import java.net.URL;
import java.util.List;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;


public class InstructionPrinter implements InsVisitor {
    public enum Type {
        PYTHON,
        INS,
        JS
    }

    private STGroup m_stg;
    
    
    public InstructionPrinter(Type ty) {
        switch (ty) {
        case PYTHON:
            setPython();
            break;
        case INS:
            setInstruction();
            break;
        case JS:
            setJS();
            break;
        default:
            throw new Error("should not see me");
        }
        
    }

    public String print(ProgramIns prog) {
        // program_st(varlst, inslst) ::= <<
        ST st = m_stg.getInstanceOf("program_st");
        
        for (TID gv: prog.getGlobalVars()) {
            st.add("vardeflst", gv);
        }
        for (UtfplInstruction ins: prog.getInsLst()) {
            st.add("inslst", ins.accept(this));
        }
        return st.render();
    }
    
    private void setPython() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/instruction/python_code.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        throw new Error("todo");

    }
    
    private void setInstruction() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/instruction/ins.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');

    }
    
    private void setJS() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/instruction/js_code.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        throw new Error("todo");

    }
    
    private ST visitInsLst(List<UtfplInstruction> inslst) {
        ST st = m_stg.getInstanceOf("inslst_st");

        for (UtfplInstruction ins: inslst) {
           st.add("inslst", ins.accept(this));
        }
        return st;    
        
    }
    
    private ST visitValPrim(ValPrim vp) {
        if (vp instanceof TID) {
            ST st = m_stg.getInstanceOf("tid_st");
            st.add("id", (TID)vp);
            return st;
        } else if (vp instanceof AtomValue) {
            ST st = m_stg.getInstanceOf("atom_value_st");
            st.add("v", (AtomValue)vp);
            return st;
        } else {
            throw new Error("shall not happen");
        }
    }
    
    @Override
    public Object visit(CondIns ins) {
        // cond_ins_st(holder, cond, btrue, bfalse) ::= <<
        ST st = m_stg.getInstanceOf("cond_ins_st");
        st.add("holder", ins.m_holder.toString());
        st.add("cond", visitValPrim(ins.m_cond));
        st.add("btrue", visitInsLst(ins.m_btrue));
        st.add("bfalse", visitInsLst(ins.m_bfalse));
        
        return st;
        
    }
    
    @Override
    public Object visit(FuncCallIns ins) {
        // func_call_ins_st(holder, funlab, args) ::= <<
        ST st = null;
        if (ins.m_holder.isVoid()) {
            st = m_stg.getInstanceOf("func_call_ins_no_ret_st");
        } else {
            st = m_stg.getInstanceOf("func_call_ins_st");
            st.add("holder", ins.m_holder);
        }
        st.add("funlab", visitValPrim(ins.m_funlab));
        for (ValPrim arg: ins.m_args) {
            st.add("args", visitValPrim(arg));
        }
        return st;        
    }

    @Override
    public Object visit(FuncDefIns ins) {
        // func_def_ins(name, paralst, body, ret) ::= <<
        ST st = m_stg.getInstanceOf("func_def_ins");
        st.add("name", ins.m_name);
        for (TID para: ins.m_paralst) {
            st.add("paralst", para);
        }
        st.add("body", visitInsLst(ins.m_body));
        st.add("ret", ins.m_ret);
        
        return st;
    }
    
    @Override
    public Object visit(MoveIns ins) {
        // move_ins_st(holder, vp) ::= <<
        ST st = m_stg.getInstanceOf("move_ins_st");

        st.add("holder", ins.m_holder);

        
        st.add("vp", visitValPrim(ins.m_vp));
        
        return st;
        
    }

//    @Override
//    public Object visit(VarDefIns ins) {
//        ST st;
//        if (ins.m_tid.getUsed()) {
//            st = m_stg.getInstanceOf("var_def_ins_used_st");
//        } else {
//            st = m_stg.getInstanceOf("var_def_ins_unused_st");
//        }
//        
//        st.add("holder", ins.m_tid);
//        return st;
//    }
}
