package jats.utfpl.csps;

import jats.utfpl.instruction.TupleValue;

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

    public String print(ProgramCSPS inputProg) {
        ST st = (ST)inputProg.accept(this);
        
        return st.render(80);
    }
    
    private ST printMain(List<CBlock> body) {
        // main_proc_st(mainlab, body) ::= <<
        ST st = m_stg.getInstanceOf("main_proc_st");
        st.add("mainlab", "main");
        st.add("body", print(body));
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
        
//        CBlock.Type preType = null;
//
//        Object previous = null;
//        
//        ListIterator<CBlock> iter = blks.listIterator();
//        
//        if (iter.hasNext()) {
//            CBlock cb = iter.next();
//            if (cb instanceof CEventBlock) {
//                preType = CBlock.Type.evt;
//                previous = cb.accept(this);
//                
//            } else if (cb instanceof CAdvancedBlock) {
//                preType = CBlock.Type.proc;
//                previous = cb.accept(this);
//            } else {
//                throw new Error("not supported");
//            }
//            while (iter.hasNext()) {
//                cb = iter.next();
//                if (cb instanceof CEventBlock) {
//                   
//                    switch (preType)
//                    {
//                    case proc: {
//                        ST st = m_stg.getInstanceOf("process_event_st");
//                        st.add("proc", previous);
//                        st.add("evt", cb.accept(this));
//                        previous = st;
//                        break;
//                    }
//                    case evt: {
//                        ST st = m_stg.getInstanceOf("event_event_st");
//                        st.add("evt1", previous);
//                        st.add("evt2", cb.accept(this));
//                        previous = st;
//                        break;
//                    }
//                    default:
//                        throw new Error("not supported");
//                    }
//                    
//                    preType = CBlock.Type.evt;
//                    
//                } else if (cb instanceof CAdvancedBlock) {
//                    switch (preType)
//                    {
//                    case proc: {
//                        ST st = m_stg.getInstanceOf("process_process_st");
//                        st.add("proc1", previous);
//                        st.add("proc2", cb.accept(this));
//                        previous = st;
//                        break;
//                    }
//                    case evt: {
//                        ST st = m_stg.getInstanceOf("event_process_st");
//                        st.add("evt", previous);
//                        st.add("proc", cb.accept(this));
//                        previous = st;
//                        break;
//                    }
//                    default:
//                        throw new Error("not supported");
//                    }
//                    
//                    preType = CBlock.Type.proc;
//                    
//                } else {
//                    throw new Error("not supported");
//                }
//            }  // end of [while]         
//        } else {
//            previous = null;
//        }
//        
//        ST st = null;
//        if (null == preType) {
//            st = m_stg.getInstanceOf("grp_empty_st");
//        } else {
//            switch (preType)
//            {
//            case proc: {
//                st = m_stg.getInstanceOf("grp_lst_proc_st");
//                break;
//            }
//            case evt: {
//                st = m_stg.getInstanceOf("grp_lst_evt_st");
//                break;
//            }
//            default:
//                throw new Error("not supported");
//            }
//            
//            st.add("lst", previous);
//        }
//
//        return st;
    }

    @Override
    public Object visit(CCondBlock blk) {
        ST st = m_stg.getInstanceOf("cond_block_st");
        // cond_block_st(cond, btrue, bfalse) ::= <<
        st.add("cond", blk.m_cond.accept(this));
        st.add("btrue", print(blk.m_tb));
        st.add("bfalse", print(blk.m_fb));
        return st;
    }

    @Override
    public Object visit(CEventBlock blk) {
        ST st = m_stg.getInstanceOf("event_block_st");
        for (CInstruction ins: blk.m_inslst) {
            st.add("inslst", ins.accept(this));
        }
        return st;
    }

    @Override
    public Object visit(CProcessCallBlock blk) {
        // process_call_block_st(lab, args) ::= <<
        ST st = m_stg.getInstanceOf("process_call_block_st");
        st.add("holder", blk.m_ret.getTID().toString());
        st.add("lab", blk.m_funlab);
        for (CTemp arg: blk.m_args) {
            st.add("args", arg.accept(this));
        }
        return st;
    }

    @Override
    public Object visit(CIMove ins) {
        ST st = null;
       // move_ins_st(dst, src, v, push, ret) ::= <<
        st = m_stg.getInstanceOf("move_ins_st");
//        st.add("v", ins.m_holder);
        
        if (ins.needStack()) {
            st.add("push", true);
        } 
//        else if (ins.isRet()) {
//            st.add("ret", true);
//        }

        st.add("dst", ins.m_holder.accept(this));
        st.add("src", ins.m_vp.accept(this));
        return st;
    }

    @Override
    public Object visit(CIFunCall ins) {
        ST st = null;
        // fun_call_ins_st(dst, lab, argsv, push, ret) ::= <<
        st = m_stg.getInstanceOf("fun_call_ins_st");
//        st.add("v", ins.m_ret);
        
        if (ins.needStack()) {
            st.add("push", true);
        } 
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
                if (!v.isOutofScope()) {
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
    public Object visit(CIProcessDef proc) {
        // proc_def_st(lab, paras, body) ::= <<
        ST st = m_stg.getInstanceOf("proc_def_st");
        st.add("lab", proc.m_name);
        
        for (CTempID para: proc.m_paras) {
            st.add("paras", para.accept(this));
            if (para.isEscaped()) {
                st.add("esc_paras", para.accept(this));
            }
        }
        
        st.add("body", print(proc.m_body));
        
        return st;
    }

    @Override
    public Object visit(ProgramCSPS prog) {
        // program_st(gvarlst, proclst, mainproc, mainlab) ::= <<
        ST st = m_stg.getInstanceOf("program_st");
        for (VariableInfo gv: prog.m_globalVars) {
            st.add("gvarlst", gv.getTID());
        }
        
        for (CIProcessDef proc: prog.m_procLst) {
            st.add("proclst", proc.accept(this));
        }
        
        st.add("mainproc", printMain(prog.m_main));
        st.add("mainlab", "main");
        
        return st;
    }

    @Override
    public Object visit(CIReturn node) {
        ST st = m_stg.getInstanceOf("return_st");
        st.add("v", node.m_id.accept(this));
        return st;
    }

}
