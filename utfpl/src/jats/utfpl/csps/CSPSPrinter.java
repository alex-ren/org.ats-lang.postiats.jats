package jats.utfpl.csps;

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
    
    public ST print(List<CBlock> blks) {
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
                        st.add("evt", previous);
                        st.add("evt", cb.accept(this));
                        previous = st;
                        break;
                    }
                    default:
                        throw new Error("not supported");
                    }
                } else if (cb instanceof CAdvancedBlock) {
                    switch (preType)
                    {
                    case proc: {
                        ST st = m_stg.getInstanceOf("process_process_st");
                        st.add("proc", previous);
                        st.add("proc", cb.accept(this));
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
        // process_call_block_st(lab, paralst) ::= <<
        ST st = m_stg.getInstanceOf("process_call_block_st");
        st.add("lab", blk.m_funlab);
        for (CTemp arg: blk.m_args) {
            st.add("paralst", arg.accept(this));
        }
        return st;
    }

    @Override
    public Object visit(CIMove ins) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(CIFunCall ins) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(CTempID v) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object visit(CTempVal v) {
        // TODO Auto-generated method stub
        return null;
    }

}
