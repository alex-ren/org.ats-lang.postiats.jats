package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

import java.net.URL;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class PATCSPSPrinter implements PNodeVisitor {

    private STGroup m_stg;

    public PATCSPSPrinter() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/patcsps/patcsps.stg");
        m_stg = new STGroupFile(fileURL, "ascii", '<', '>');
    }
    

    public String print(PModel model) {
        ST st = (ST) model.accept(this);

        return st.render(80);
    }


    @Override
    public Object visit(PGDecVar node) {
        // pgdecvar_st
        ST st = m_stg.getInstanceOf("pgdevar_st");
        st.add("id", node.m_tid);
        if (node.m_exp != null) {
            st.add("init", node.m_exp.accept(this));
        }
        
        return st;
    }

    @Override
    public Object visit(PProcBranch node) {
        ST st = null;
        switch (node.m_ty) {
        case ifcommon:
            st = m_stg.getInstanceOf("pprocbranch_ifcommon_st");
            break;
        case ifa:
            st = m_stg.getInstanceOf("pprocbranch_ifa_st");
            break;
        case ifb:
            st = m_stg.getInstanceOf("pprocbranch_ifb_st");
            break;
        }
        st.add("cond", node.m_condExp.accept(this));
        st.add("if_proc", node.m_ifProc.accept(this));
        if (node.m_elseProc != null) {
            st.add("else_proc", node.m_elseProc.accept(this));
        }
        
        return st;
    }

    @Override
    public Object visit(PEvent node) {
        ST st = m_stg.getInstanceOf("pevent_st");
        for (PStat stat: node.m_statLst) {
            st.add("stat_lst", stat.accept(this));
        }
        return st;        
    }

    @Override
    public Object visit(PExpFuncCall node) {
        ST st = m_stg.getInstanceOf("pexpfunccall_st");
        st.add("fun_lab", node.m_funLab);
        for (PExp arg: node.m_argLst) {
            st.add("arg_lst", arg.accept(this));
        }
        
        return st;
    }

    @Override
    public Object visit(PStatLocalVarDec node) {
        ST st = m_stg.getInstanceOf("pstatlocalvardec_st");
        st.add("name", node.m_name);
        st.add("val", node.m_val.accept(this));
        return st;
    }

    @Override
    public Object visit(PStatAssignment node) {
        ST st = m_stg.getInstanceOf("pstatassignment_st");
        st.add("name", node.m_name);
        st.add("val", node.m_val.accept(this));
        return st;
    }

    @Override
    public Object visit(PExpAtom node) {
        return node.toString();
    }

    @Override
    public Object visit(PProcAtom node) {
        ST st = m_stg.getInstanceOf("pprocatom_st");
        return st;
    }

    @Override
    public Object visit(PProcCall node) {
        ST st = m_stg.getInstanceOf("pproccall_st");
        st.add("name", node.m_name.toString());
        for (PExp arg: node.m_paraLst) {
            st.add("arg_lst", arg.accept(this));
        }
        return st;
        
    }

    @Override
    public Object visit(PExpID node) {
        Aux.Address addr = node.m_tid.getAddr();
        
        if (null != addr) {
            return addr.toString();
        } else {
            return node.m_tid.toString();
        }
        
    }

    @Override
    public Object visit(PModel node) {
        // pmodel_st(scheduler_body, gvar_lst, proc_lst, thread_lst, main_proc_body) ::= <<
        ST st = m_stg.getInstanceOf("pmodel_st");

        for (PGDecVar gv: node.m_gvLst) {
            st.add("gvar_lst", gv.accept(this));
        }

        st.add("main_proc_body", node.m_mainProcBody.accept(this));
        
        for (PGDecProc proc: node.m_procLst) {
            st.add("proc_lst", proc.accept(this));
        }
        
        st.add("scheduler_body", node.m_SchedulerBody.accept(this));
        
        for (PGDecProc thread: node.m_threadLst) {
            st.add("thread_lst", thread.accept(this));
        }
        
        return st;
    }

    @Override
    public Object visit(PGDecProc node) {
        ST st = m_stg.getInstanceOf("pgdecproc_st");
        st.add("name", node.m_name.toString());
        for (TID para: node.m_paraLst) {
            st.add("para_lst", para.toString());
        }
        
        for (TID para: node.m_escParaLst) {
            st.add("esc_para_lst", para.toString());
        }
        
        st.add("body", node.m_body.accept(this));
        return st;
        
    }

    @Override
    public Object visit(PProcSeq node) {
        ST st = m_stg.getInstanceOf("pprocseq_st");
        st.add("proc_left", node.m_procLeft.accept(this));
        st.add("proc_right", node.m_procRight.accept(this));
        return st;
    }

    @Override
    public Object visit(PExpStackOpr node) {
        ST st = null;
        if (node.m_tid.isBool()) {
            st = m_stg.getInstanceOf("pexpstackopr_bool_t");
        } else {
            st = m_stg.getInstanceOf("pexpstackopr_default_t");
        }
        
        st.add("frame", node.m_frame);
        st.add("pos", node.m_pos);
        
        return st;
    }

    @Override
    public Object visit(PProcEvent node) {
        ST st = m_stg.getInstanceOf("pprocevent_st");
        st.add("evt", node.m_evt.accept(this));
        st.add("proc", node.m_proc.accept(this));
        return st;
    }

    @Override
    public Object visit(PInclude node) {
        throw new Error("Not supported");
    }

    @Override
    public Object visit(PGDecChan node) {
        throw new Error("Not supported");
    }

    @Override
    public Object visit(PProcChannel node) {
        ST st = m_stg.getInstanceOf("pprocchannel_st");
        st.add("ch", node.m_ch.accept(this));
        st.add("proc", node.m_proc.accept(this));
        return st;
    }

    @Override
    public Object visit(PChannelRecv node) {
        ST st = m_stg.getInstanceOf("pchannelrecv_st");
        st.add("name", node.m_name.toString());
        for (TID ele: node.m_eleLst) {
            st.add("ele_lst", ele.toString());
        }
        
        return st;
    }

    @Override
    public Object visit(PChannelSend node) {
        ST st = m_stg.getInstanceOf("pchannelsend_st");
        st.add("name", node.m_name.toString());
        for (PExp ele: node.m_msgLst) {
            st.add("msg_lst", ele.accept(this));
        }
        
        return st;
    }
// {plus, minus, mul, div, gt, gte, lt, lte, eq, inc, dec};
    @Override
    public Object visit(PExpOpr node) {
        ST st = null;
        if (PExpOpr.isUnary(node.m_type)) {
            st = m_stg.getInstanceOf("pexpopr_unary_st");
            st.add("exp", node.m_opr1.accept(this));
        } else {
            st = m_stg.getInstanceOf("pexpopr_binary_st");
            st.add("exp1", node.m_opr1.accept(this));
            st.add("exp2", node.m_opr2.accept(this));
        }
        
        st.add("opr", PExpOpr.stringType(node.m_type));
        return st;
    }

    @Override
    public Object visit(PProcParallel node) {
        ST st = m_stg.getInstanceOf("pprocparallel_st");
        for (PProc proc: node.m_procLst) {
            st.add("proc_lst", proc.accept(this));
        }
        return st;
    }


    @Override
    public Object visit(PExpStackPush node) {
        ST st = m_stg.getInstanceOf("pexpstackpush_st");
        st.add("name", node.m_exp.accept(this));
        return st;
    }


    @Override
    public Object visit(PStatReturn node) {
        ST st = m_stg.getInstanceOf("pstatreturn_st");
        st.add("v", node.m_v.accept(this));
        return st;
    }


    @Override
    public Object visit(PExpTuple node) {
        if (node != PExpTuple.cNone) {
            throw new Error("not supported");
        }
        ST st = m_stg.getInstanceOf("pnone_st");
        return st;
        
    }


}













