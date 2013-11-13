package jats.utfpl.csps;

import jats.utfpl.instruction.TupleValue;
import jats.utfpl.patcsps.type.PATType;
import jats.utfpl.patcsps.type.PATTypeArray;

import java.net.URL;
import java.util.List;
import java.util.ListIterator;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class CSPSPrinter implements CSPSVisitor {
    private STGroup m_stg;
    
    public CSPSPrinter() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/csps/csps.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');    
    }

    public String printProgram(ProgramCSPS inputProg) {
        ST st = print(inputProg);
        
        return st.render(80);
    }
    
    private ST printMain(List<CBlock> body) {
        // main_proc_st(mainlab, body) ::= <<
        ST st = m_stg.getInstanceOf("main_proc_st");
        st.add("mainlab", "main");
        st.add("body", print(body));
        return st;        
    }
    

    public ST print(FunctionCSPS proc) {
        // proc_def_st(lab, paras, body) ::= <<
        ST st = m_stg.getInstanceOf("proc_def_st");
        st.add("lab", proc.m_name);
        
        for (CTempID para: proc.m_paras) {
            st.add("paras", para.accept(this));
        }
        
        st.add("body", print(proc.m_body));
        
        return st;
    }

    public ST print(ProgramCSPS prog) {
        // program_st(gvarlst, proclst, mainproc, mainlab) ::= <<
        ST st = m_stg.getInstanceOf("program_st");
        for (VariableInfo gv: prog.m_globalVars) {
            PATType ty = gv.getTID().getType();
            if (ty instanceof PATTypeArray) {
                ST st_gv = m_stg.getInstanceOf("garray_def_st");
                st_gv.add("gvar", gv.getTID());
                st_gv.add("sz", ((PATTypeArray)ty).getSize());

                st.add("gvarlst", st_gv);
                
            } else {
                ST st_gv = m_stg.getInstanceOf("gvar_def_st");
                st_gv.add("gvar", gv.getTID());

                st.add("gvarlst", st_gv);
            }
            
        }
        
        for (FunctionCSPS proc: prog.m_procLst) {
            st.add("proclst", print(proc));
        }
        
        st.add("mainproc", printMain(prog.m_main));
        st.add("mainlab", "main");
        
        return st;
    }


    private ST print(List<CBlock> blks) {
        // blk_lst_st(blklst) ::= <<
        ST st = m_stg.getInstanceOf("blk_lst_st");
        ListIterator<CBlock> iter = blks.listIterator();
        while (iter.hasNext()) {
            st.add("blklst", iter.next().accept(this));
        }
        return st;
    }

    @Override
    public Object visit(CBCond blk) {
        ST st = m_stg.getInstanceOf("cond_block_st");
        // cond_block_st(cond, btrue, bfalse) ::= <<
        st.add("cond", blk.m_cond.accept(this));
        st.add("btrue", print(blk.m_tb));
        st.add("bfalse", print(blk.m_fb));
        return st;
    }

    @Override
    public Object visit(CBEvent blk) {
        ST st = m_stg.getInstanceOf("event_block_st");
        for (CInstruction ins: blk.m_inslst) {
            st.add("inslst", ins.accept(this));
        }
        return st;
    }

    @Override
    public Object visit(CBProc blk) {
        // process_call_block_st(lab, args) ::= <<
        ST st = m_stg.getInstanceOf("process_call_block_st");
        st.add("holder", blk.m_ret.accept(this));
        st.add("lab", blk.m_funlab);
        for (CTemp arg: blk.m_args) {
            st.add("args", arg.accept(this));
        }
        return st;
    }

    @Override
    public Object visit(CIBind ins) {
        ST st = null;
       // bind_ins_st(dst, src, v, escape, ret) ::= <<
        st = m_stg.getInstanceOf("bind_ins_st");
//        st.add("v", ins.m_holder);
        st.add("escape", ins.m_holder.isEscaped());
        st.add("dst", ins.m_holder.accept(this));
        st.add("src", ins.m_vp.accept(this));
        return st;
    }

    @Override
    public Object visit(CIFunCall ins) {
        ST st = null;
        // fun_call_ins_st(dst, lab, argsv, escape, ret) ::= <<
        st = m_stg.getInstanceOf("fun_call_ins_st");
//        st.add("v", ins.m_ret);
        st.add("isdef", ins.m_ret.isDefinition());
        st.add("escape", ins.m_ret.isEscaped());
//        else if (ins.isRet()) {
//            st.add("ret", true);
//        }

        st.add("dst", ins.m_ret.accept(this));
        st.add("lab", ins.m_funlab);
        
        for (CTemp arg: ins.m_args) {
            st.add("args", arg.accept(this));
        }
        return st;
    }

    @Override
    public Object visit(CTempID v) {
        ST st = null;
        if (v.getTID().isGlobal()) {
            st = m_stg.getInstanceOf("global_id_st");
            st.add("id", v);
        } else {  // not global variable
            if (v.isDefinition()) {
                if (v.isPara()) {
                    st = m_stg.getInstanceOf("para_def_st");
                    st.add("v", v);
                } else {
                    st = m_stg.getInstanceOf("val_def_st");
                    st.add("v", v);
                }

            } else {  // this is the usage
                if (null == v.getStackInfo()) {
                    st = m_stg.getInstanceOf("val_use_name_st");
                    st.add("v", v);
                } else {
                    if (v.getTID().isBool()) {
                        st = m_stg.getInstanceOf("val_use_stack_bool_st");
                    } else {
                        st = m_stg.getInstanceOf("val_use_stack_st");
                    }
                    st.add("v", v);
                    st.add("loc", v.getStackInfo());
                }
            }
        }
        return st;
    }

    @Override
    public Object visit(CTempVal v) {
        ST st = null;
        switch (v.m_type)
        {
            case atom:
                // atom_val_st(v) ::= <<
                st = m_stg.getInstanceOf("atom_val_st");
                st.add("v", v.getAtomValue());
                return st;
            case tuple:
                st = m_stg.getInstanceOf("tuple_val_st");
                if (v.getTupleValue() == TupleValue.cNone) {
                    return st;
                } else {
                    throw new Error("Not supported");
                }
            default:
                throw new Error("shall not happen");
        }

    }

    @Override
    public Object visit(CIReturn node) {
        ST st = m_stg.getInstanceOf("return_ins_st");
        st.add("v", node.m_v.accept(this));
        return st;
    }

    @Override
    public Object visit(CILoad node) {
        ST st = m_stg.getInstanceOf("load_ins_st");
        st.add("dst", node.m_localHost.accept(this));
        st.add("src", node.m_globalVar.accept(this));
        
        st.add("escape", node.m_localHost.isEscaped());
        return st;
    }

    @Override
    public Object visit(CILoadArray node) {
        // load_array_ins_st(src, ind, dst, escape) ::= <<
        ST st = m_stg.getInstanceOf("load_array_ins_st");
        st.add("dst", node.m_localHolder.accept(this));
        st.add("src", node.m_globalVar.accept(this));
        st.add("ind", node.m_localIndex.accept(this));
        
        st.add("escape", node.m_localHolder.isEscaped());
        return st;
    }

    @Override
    public Object visit(CIMutexAlloc node) {
        // mutex_alloc_ins_st(dst, escape) ::= <<
        ST st = m_stg.getInstanceOf("mutex_alloc_ins_st");
        st.add("dst", node.m_holder.accept(this));
        st.add("escape", node.m_holder.isEscaped());
        
        return st;
    }

    @Override
    public Object visit(CIStore node) {
        // store_ins_st(src, dst) ::= <<
        ST st = m_stg.getInstanceOf("store_ins_st");
        st.add("src", node.m_localSrc.accept(this));
        st.add("dst", node.m_globalVar.accept(this));
        
        return st;
    }

    @Override
    public Object visit(CIStoreArray node) {
        // store_array_ins_st(src, dst, ind) ::= <<
        ST st = m_stg.getInstanceOf("store_array_ins_st");
        st.add("src", node.m_localValue.accept(this));
        st.add("dst", node.m_globalVar.accept(this));
        st.add("ind", node.m_localIndex.accept(this));
        
        return st;
    }

    @Override
    public Object visit(CICond node) {
        // cond_ins_st(holder, cond, tbranch, fbranch) ::= <<
        ST st = m_stg.getInstanceOf("cond_ins_st");
        st.add("cond", node.m_cond.accept(this));
        for (CInstruction ins: node.m_true) {
            st.add("tbranch", ins.accept(this));
        }
        for (CInstruction ins: node.m_false) {
            st.add("fbranch", ins.accept(this));
        }
        
        return st;
    }

    @Override
    public Object visit(CIVarDef node) {
        // vardef_ins_st(holder) ::= <<
        ST st = m_stg.getInstanceOf("vardef_ins_st");
        st.add("holder", node.m_id.accept(this));
        
        return st;
    }

    @Override
    public Object visit(CIAssign node) {
        // assign_ins_st(src, dst) ::= <<
        ST st = m_stg.getInstanceOf("assign_ins_st");
        st.add("dst", node.m_holder.accept(this));
        st.add("src", node.m_vp.accept(this));
        return st;
    }

}
