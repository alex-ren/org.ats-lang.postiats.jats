package jats.utfpl.csps;

import jats.utfpl.instruction.TID;

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
        // program_st(gvarlst, proclst, mainproc, mainlab) ::= <<
        ST st = m_stg.getInstanceOf("program_st");
        for (TID gv: inputProg.m_globalVars) {
            st.add("gvarlst", gv);
        }
        
        for (CIProcessDef proc: inputProg.m_procLst) {
            st.add("proclst", proc.accept(this));
        }
        
        st.add("mainproc", printMain(inputProg.m_main));
        st.add("mainlab", "main");
        
        return st.render();
        
    }
    
    private ST printMain(List<CBlock> body) {
        // main_proc_st(mainlab, body) ::= <<
        ST st = m_stg.getInstanceOf("main_proc_st");
        st.add("mainlab", "main");
        st.add("body", print(body));
        return st;        
    }
    
    private ST print(List<CBlock> blks) {
        ListIterator<CBlock> iter = blks.listIterator();
        
        CBlock.Type preType = null;

        Object previous = null;
        
        if (iter.hasNext()) {
            CBlock cb = iter.next();
            if (cb instanceof CEventBlock) {
                preType = CBlock.Type.evt;
                previous = cb.accept(this);
                
            } else if (cb instanceof CAdvancedBlock) {
                preType = CBlock.Type.proc;
                previous = cb.accept(this);
            } else {
                throw new Error("not supported");
            }
            while (iter.hasNext()) {
                cb = iter.next();
                if (cb instanceof CEventBlock) {
                   
                    switch (preType)
                    {
                    case proc: {
                        ST st = m_stg.getInstanceOf("process_event_st");
                        st.add("proc", previous);
                        st.add("evt", cb.accept(this));
                        previous = st;
                        break;
                    }
                    case evt: {
                        ST st = m_stg.getInstanceOf("event_event_st");
                        st.add("evt1", previous);
                        st.add("evt2", cb.accept(this));
                        previous = st;
                        break;
                    }
                    default:
                        throw new Error("not supported");
                    }
                    
                    preType = CBlock.Type.evt;
                    
                } else if (cb instanceof CAdvancedBlock) {
                    switch (preType)
                    {
                    case proc: {
                        ST st = m_stg.getInstanceOf("process_process_st");
                        st.add("proc1", previous);
                        st.add("proc2", cb.accept(this));
                        previous = st;
                        break;
                    }
                    case evt: {
                        ST st = m_stg.getInstanceOf("event_process_st");
                        st.add("evt", previous);
                        st.add("proc", cb.accept(this));
                        previous = st;
                        break;
                    }
                    default:
                        throw new Error("not supported");
                    }
                    
                    preType = CBlock.Type.proc;
                    
                } else {
                    throw new Error("not supported");
                }
            }  // end of [while]         
        } else {
            previous = null;
        }
        
        ST st = null;
        switch (preType)
        {
        case proc: {
            st = m_stg.getInstanceOf("grp_lst_proc_st");
            break;
        }
        case evt: {
            st = m_stg.getInstanceOf("grp_lst_evt_st");
            break;
        }
        default:
            throw new Error("not supported");
        }
        
        st.add("lst", previous);

        return st;
    }

    @Override
    public Object visit(CCondBlock blk) {
        ST st = m_stg.getInstanceOf("cond_block_st");
        // cond_block_st(cond, btrue, bfalse) ::= <<
        st.add("cond", blk.m_cond.accept(this, blk));
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
        st.add("lab", blk.m_funlab);
        for (CTemp arg: blk.m_args) {
            st.add("args", arg.accept(this, blk));
        }
        return st;
    }

    @Override
    public Object visit(CIMove ins, CBlock curBlk) {
        // move_ins_st(dst, src) ::= <<
        ST st = m_stg.getInstanceOf("move_ins_st");
        st.add("dst", ins.m_holder.accept(this, curBlk));
        st.add("src", ins.m_vp.accept(this, curBlk));
        return st;
    }

    @Override
    public Object visit(CIFunCall ins, CBlock curBlk) {
        // fun_call_ins_st(dst, lab, args) ::= <<
        ST st = m_stg.getInstanceOf("fun_call_ins_st");
        st.add("dst", ins.m_ret.accept(this, curBlk));
        st.add("lab", ins.m_funlab);
        for (CTemp arg: ins.m_args) {
            st.add("args", arg.accept(this, curBlk));
        }
        return st;
    }

    @Override
    public Object visit(CTempID v, CBlock curBlk) {
        ST st = null;
        if (v.getTID().isGlobal()) {
            st = m_stg.getInstanceOf("global_id_st");
            st.add("id", v);
        } else {
            st = m_stg.getInstanceOf("temp_val_st");
            st.add("v", v);
        }
        return st;
    }

    @Override
    public Object visit(CTempVal v, CBlock curBlk) {
        // temp_val_st(v) ::= <<
        ST st = m_stg.getInstanceOf("temp_val_st");
        st.add("v", v.m_v);
        return st;
    }

    @Override
    public Object visit(CIProcessDef proc) {
        // proc_st(lab, paras, body) ::= <<
        ST st = m_stg.getInstanceOf("proc_st");
        st.add("lab", proc.m_name);
        
        for (CTempID para: proc.m_paras) {
            st.add("paras", para.accept(this, null));
        }
        
        st.add("body", print(proc.m_body));
        
        return st;
    }

}
