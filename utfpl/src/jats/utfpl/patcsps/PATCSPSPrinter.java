package jats.utfpl.patcsps;

import jats.utfpl.instruction.GlobalExtCode;
import jats.utfpl.instruction.TID;

import java.net.URL;
import java.util.Iterator;

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
    public Object visit(PModel node) {
        // pmodel_st(scheduler_body, gvar_lst, proc_lst, main_proc_body, ext_code_lst) ::= <<
        ST st = m_stg.getInstanceOf("pmodel_st");

        for (PGDec gv: node.m_gvLst) {
            st.add("gvar_lst", gv.accept(this));
        }

        st.add("main_proc_body", node.m_mainProcBody.accept(this));
        
        for (PGDecProc proc: node.m_procLst) {
            st.add("proc_lst", proc.accept(this));
        }
        
        for (GlobalExtCode extCode: node.m_extCodeLst) {
            st.add("ext_code_lst", extCode.m_content);
        }
        
        // scheduler_body_st(proc1, proc_lst) ::= <<
        ST stSch = m_stg.getInstanceOf("scheduler_body_st");
        
        Iterator<PGDecProc> iter = node.m_threadLst.iterator();
        if (iter.hasNext()) {
            stSch.add("proc1", iter.next().m_name);
            
            while (iter.hasNext()) {
            	stSch.add("proc_lst", iter.next().m_name);
            }
        }
        
        st.add("scheduler_body", stSch);
        
        return st;
    }

    @Override
    public Object visit(PGDecVar node) {
        // pgdecvar_st
        ST st = m_stg.getInstanceOf("pgdecvar_st");
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
        // pstatlocalvardec_st(name, val, is_global) ::= <<
        ST st = m_stg.getInstanceOf("pstatlocalvardec_st");
        st.add("name", node.m_name);
        st.add("val", node.m_val.accept(this));
        st.add("is_global", node.m_name.isGlobalValue());
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
    public Object visit(PGDecProc node) {
        ST st = m_stg.getInstanceOf("pgdecproc_st");
        st.add("name", node.m_name.toString());
//        for (TID para: node.m_paraLst) {
//            st.add("para_lst", para.toString());
//        }
        
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
    public Object visit(PExpStackGet node) {
        ST st = null;
        if (node.m_tid.isBool()) {
            st = m_stg.getInstanceOf("pexpstackopr_bool_t");
        } else if (node.m_tid.isInt()) {
            st = m_stg.getInstanceOf("pexpstackopr_int_t");
        }  else {
            st = m_stg.getInstanceOf("pexpstackopr_default_t");
        }
        
        st.add("frame", 0);
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
    public Object visit(PStatStackPush node) {
        ST st = m_stg.getInstanceOf("pstatstackpush_st");
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

    @Override
    public Object visit(PGDecArray node) {
        // pgdecarray_st(id, sz) ::= <<
        ST st = m_stg.getInstanceOf("pgdecarray_st");
        st.add("id", node.m_tid);
        st.add("sz", node.m_sz);
        
        return st;
    }


    @Override
    public Object visit(PInsCond node) {
        // pinscond_st(cond, trueb, falseb) ::= <<
        ST st = m_stg.getInstanceOf("pinscond_st");
        st.add("cond", node.m_cond.accept(this));
        for (PStat stat: node.m_true) {
            st.add("trueb", stat.accept(this));
        }
        
        for (PStat stat: node.m_false) {
            st.add("falseb", stat.accept(this));
        }
        
        return st;
    }

    // check here todo

    @Override
    public Object visit(PInsLoad node) {
        // pinsload_st(src, dst) ::= <<
        ST st = m_stg.getInstanceOf("pinsload_st");
        st.add("src", node.m_globalVar);
        st.add("dst", node.m_localHolder);
        
        return st;
    }


    @Override
    public Object visit(PInsLoadArray node) {
        // pinsloadarray_st(src, index, dst, is_global) ::= <<
        ST st = m_stg.getInstanceOf("pinsloadarray_st");
        st.add("src", node.m_globalVar);
        st.add("dst", node.m_localHolder);
        st.add("index", node.m_localIndex.accept(this));
        
        if (node.m_localHolder.isGlobal()) {  // assign to a global value
            st.add("is_global", true);
        } else {
            st.add("is_global", false);
        }
        
        return st;
    }


    @Override
    public Object visit(PInsStore node) {
        // pinsstore_st(src, dst) ::= <<
        ST st = m_stg.getInstanceOf("pinsstore_st");
        st.add("dst", node.m_globalVar);
        st.add("src", node.m_localSrc.accept(this));
        
        return st;
    }


    @Override
    public Object visit(PInsStoreArray node) {
        // pinsstorearray_st(src, dst, index) ::= <<
    	ST st = m_stg.getInstanceOf("pinsstorearray_st");
    	st.add("src", node.m_localSrc.accept(this));
    	st.add("dst", node.m_globalVar);
    	st.add("index", node.m_localIndex.accept(this));
    	
    	return st;
    }


    @Override
    public Object visit(PStatProcCallPrelogue node) {
        // pstatproccallprelogue_st(args, is_tail_call) ::= <<
        ST st = m_stg.getInstanceOf("pstatproccallprelogue_st");
        for (PExp exp: node.m_args) {
            st.add("args", exp.accept(this));
        }
        st.add("is_tail_call", node.m_isTailCall);
        
        return st;
    }


    @Override
    public Object visit(PStatProcCallEpilogue node) {
        // pstatproccallepilogue_st(ret, is_global) ::= <<
    	ST st = m_stg.getInstanceOf("pstatproccallepilogue_st");
    	if (!node.m_ret.isAnony()) {
    		st.add("ret", node.m_ret);
    		st.add("is_global", node.m_ret.isGlobal());
    	}
    	
    	return st;
    }


	@Override
    public Object visit(PProcThreadCreate node) {
	    // pprocthreadcreate_st(tid, funaddr, args) ::= <<
		ST st = m_stg.getInstanceOf("pprocthreadcreate_st");
		st.add("tid", node.m_tid.accept(this));
		st.add("funaddr", node.m_funlab.getAddr());
		st.add("args", node.m_args.accept(this));
		
		return st;
    }



    @Override
    public Object visit(PInsMutexAlloc node) {
        // pinsmutexalloc_st(holder, is_global) ::= <<
    	ST st = m_stg.getInstanceOf("pinsmutexalloc_st");
    	st.add("holder", node.m_holder);
    	if (node.m_holder.isGlobal()) {
    		st.add("is_global", true);
    	} else {
    		st.add("is_global", false);
    	}
    	
    	return st;
    }

	@Override
    public Object visit(PInsMutexRelease node) {
        // pinsmutexrelease_st(mutex) ::= <<
    	ST st = m_stg.getInstanceOf("pinsmutexrelease_st");
    	st.add("mutex", node.m_mutex.accept(this));
    	
    	return st;
    }


    @Override
    public Object visit(PInsCondAlloc node) {
        // pinscondalloc_st(holder, is_global) ::= <<
    	ST st = m_stg.getInstanceOf("pinscondalloc_st");
    	st.add("holder", node.m_holder);
    	if (node.m_holder.isGlobal()) {
    		st.add("is_global", true);
    	} else {
    		st.add("is_global", false);
    	}
    	
    	return st;
    }

	@Override
    public Object visit(PInsCondRelease node) {
        // pinscondrelease_st(cond) ::= <<
    	ST st = m_stg.getInstanceOf("pinscondrelease_st");
    	st.add("cond", node.m_cond.accept(this));
    	
    	return st;
    }


    @Override
    public Object visit(PInsMCAssert node) {
        // pinsmcassert_st(pred) ::= <<
        ST st = m_stg.getInstanceOf("pinsmcassert_st");
        st.add("pred", node.m_localSrc.accept(this));
        
        return st;
    }

}













