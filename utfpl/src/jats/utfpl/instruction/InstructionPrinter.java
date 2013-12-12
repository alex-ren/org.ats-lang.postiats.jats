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

    public String print(ProgramInstruction prog) {
        // program_st(varlst, inslst) ::= <<
        ST st = m_stg.getInstanceOf("program_st");
        
        for (GlobalEntity gv: prog.getGlobalEntities()) {
            ST stGV = null;
            if (gv instanceof GlobalArray) {
                stGV = m_stg.getInstanceOf("global_array_st");
                stGV.add("id", gv.getTID());
                stGV.add("size", ((GlobalArray)gv).getSize());
            } else if (gv instanceof GlobalValue) {
                stGV = m_stg.getInstanceOf("global_value_st");
                stGV.add("id", gv.getTID());
            } else if (gv instanceof GlobalVariable) {
                stGV = m_stg.getInstanceOf("global_variable_st");
                stGV.add("id", gv.getTID());
            } else {
                throw new Error("not supported");
            }
            st.add("gv_def_lst", stGV);
        }
        
        for (UtfplInstruction ins: prog.getInsLst()) {
            st.add("ins_lst", ins.accept(this));
        }
        if (null != prog.getFuncLst()) {
            for (FunctionInstruction func: prog.getFuncLst()) {
                st.add("func_lst", printFunction(func));
            }
        }

        return st.render();
    }
    
    public Object printFunction(FunctionInstruction func) {
        // func_def_ins(name, paralst, body, ret) ::= <<
        ST st = m_stg.getInstanceOf("func_def");
        st.add("name", func.getName());
        for (TID para: func.getParaLst()) {
            st.add("paralst", para);
        }
        for (TID para: func.getEscParaLst()) {
            st.add("paralst", para);
        }
        st.add("body", visitInsLst(func.getBody()));
        
        return st;
    }
    
    private void setPython() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/instruction/python_code.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        throw new Error("not supported");

    }
    
    private void setInstruction() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/instruction/ins.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');

    }
    
    private void setJS() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/instruction/js_code.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
        throw new Error("not supported");

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
        } else if (vp instanceof TupleValue) {
            if (vp != TupleValue.cNone) {
                throw new Error("Not supported");
            }
            ST st = m_stg.getInstanceOf("tuple_value_st");
            return st;
        } else {
            System.out.println("vvvvvvvvvvvvv vp is " + vp);
            throw new Error("shall not happen");
        }
    }
    
    @Override
    public Object visit(InsCond ins) {
        // cond_ins_st(holder, cond, btrue, bfalse) ::= <<
        ST st = m_stg.getInstanceOf("cond_ins_st");
        st.add("holder", ins.m_holder);
        st.add("cond", visitValPrim(ins.m_cond));
        st.add("btrue", visitInsLst(ins.m_btrue));
        st.add("bfalse", visitInsLst(ins.m_bfalse));
        
        return st;
        
    }
    
    @Override
    public Object visit(InsCall ins) {
        // func_call_ins_st(holder, funlab, args) ::= <<
        ST st = null;
//        if (ins.m_holder.isVoid()) {
//            st = m_stg.getInstanceOf("func_call_ins_no_ret_st");
//        } else {
            st = m_stg.getInstanceOf("func_call_ins_st");
            st.add("holder", ins.m_holder);
//        }
        st.add("funlab", visitValPrim(ins.m_funlab));
        for (ValPrim arg: ins.m_args) {
            st.add("args", visitValPrim(arg));
        }
        st.add("is_tail", ins.m_isTailCall);
        return st;        
    }

    @Override
    public Object visit(InsFuncDef ins) {
        // func_def_ins(name, paralst, body, ret) ::= <<
        ST st = m_stg.getInstanceOf("func_def_ins");
        st.add("name", ins.m_name);
        for (TID para: ins.m_paralst) {
            st.add("paralst", para);
        }
        st.add("body", visitInsLst(ins.m_body));
        st.add("ret", ins.m_ret);
        
        return st;
//        throw new Error("shall not happen");
    }
    
    @Override
    public Object visit(InsMove ins) {
        // move_ins_st(holder, vp) ::= <<
        ST st = m_stg.getInstanceOf("move_ins_st");

        st.add("holder", ins.m_holder);

        st.add("vp", visitValPrim(ins.m_vp));
        
        return st;
        
    }

	@Override
    public Object visit(InsFuncGroup ins) {
	    ST st = m_stg.getInstanceOf("func_group_ins_st");
	    for (InsFuncDef fundef: ins.m_funLst) {
	    	st.add("insLst", fundef.accept(this));
	    }
	    return st;
//	    throw new Error("shall not happen");
    }
	
    @Override
    public Object visit(InsStoreArray ins) {
        ST st = m_stg.getInstanceOf("store_array_ins_st");
        st.add("lv", ins.m_localValue);
        st.add("gv", ins.m_globalVar);
        st.add("index", ins.m_localIndex);
        
        return st;
    }

    @Override
    public Object visit(InsStore ins) {
        ST st = m_stg.getInstanceOf("store_ins_st");
        st.add("lv", ins.m_localSrc);
        st.add("gv", ins.m_globalDest);
        
        return st;
    }

    @Override
    public Object visit(InsRet ins) {
        ST st = m_stg.getInstanceOf("ret_ins_st");
        if (!ins.isVoid()) {
            st.add("v", ins.m_v);
        }
        return st;
    }

    @Override
    public Object visit(InsLoadArray ins) {
        ST st = m_stg.getInstanceOf("load_array_ins_st");
        st.add("gv", ins.m_globalVar);
        st.add("index", ins.m_localIndex);
        st.add("lv", ins.m_localHolder);
        
        return st;
    }

    @Override
    public Object visit(InsAllocMutex ins) {
        ST st = m_stg.getInstanceOf("allocate_mutex_ins_st");
        
        st.add("holder", ins.m_holder);
        
        return st;
    }

    @Override
    public Object visit(InsLoad ins) {
        ST st = m_stg.getInstanceOf("load_ins");
        st.add("gv", ins.m_globalVar);
        st.add("lv", ins.m_localHolder);
        
        return st;
    }

}
