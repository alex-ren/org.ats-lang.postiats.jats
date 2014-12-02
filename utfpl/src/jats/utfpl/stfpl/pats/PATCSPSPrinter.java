package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.AuxMCIns.Address;
import jats.utfpl.stfpl.mcinstruction.MCGlobalExtCode;
import jats.utfpl.stfpl.mcinstruction.MCSId;
import jats.utfpl.stfpl.stype.AuxSType;

import java.net.URL;
import java.util.Iterator;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

public class PATCSPSPrinter implements PNodeVisitor {

    private STGroup m_stg;

    public PATCSPSPrinter() {
        URL fileURL = this.getClass().getResource("/jats/utfpl/stfpl/pats/pats.stg");
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
        
        for (MCGlobalExtCode extCode: node.m_extCodeLst) {
            st.add("ext_code_lst", extCode.m_content);
        }
        
        // scheduler_body_st(proc1_name, proc1_addr, else_procs) ::= <<
        ST stSch = m_stg.getInstanceOf("scheduler_body_st");
        
        Iterator<PGDecProc> iter = node.m_threadLst.iterator();
        if (iter.hasNext()) {
        	PGDecProc proc1 = iter.next();
            stSch.add("proc1_name", proc1.m_name.toStringMCIns());
            stSch.add("proc1_addr", proc1.m_name.getAddr().toStringMCIns());
            
            while (iter.hasNext()) {
            	PGDecProc proc_else = iter.next();
            	
            	// schedule_else_if(proc_name, proc_addr) ::= <<
            	ST st_else = m_stg.getInstanceOf("schedule_else_if");
            	st_else.add("proc_name", proc_else.m_name.toStringMCIns());
            	st_else.add("proc_addr", proc_else.m_name.getAddr().toStringMCIns());
            	
            	stSch.add("else_procs", st_else);
            }
        }
        
        st.add("scheduler_body", stSch);
        
        return st;
    }

    @Override
    public Object visit(PGDecVar node) {
        // PGDecVar_st(id, init) ::= <<
        ST st = m_stg.getInstanceOf("PGDecVar_st");
        st.add("id", node.m_mid.toStringMCIns());
        st.add("init", 0);
//        if (node.m_exp != null) {
//            st.add("init", node.m_exp.accept(this));
//        }
        
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
    public Object visit(PNodeEvent node) {
    	// pevent_st(tag, stat_lst) ::= <<
        ST st = m_stg.getInstanceOf("pevent_st");
        st.add("tag", node.m_funname.toStringMCIns() + "__" + node.m_no);
        for (PStat stat: node.m_statLst) {
            st.add("stat_lst", stat.accept(this));
        }
        return st;        
    }

    @Override
    public Object visit(PExpFuncCall node) {
    	// PExpFuncCall_st(is_sym, fun_lab, arg_lst) ::= <<
        ST st = m_stg.getInstanceOf("PExpFuncCall_st");
        
        String fname = node.m_funLab.toStringMCIns();
        if (node.m_funLab.isSym()) {
        	st.add("is_sym", true);
        	fname = AuxPats.sym2name(fname);
        } else {
        	st.add("is_sym", false);
        }
        
        st.add("fun_lab", fname);
    	
        for (PExp arg: node.m_argLst) {
            st.add("arg_lst", arg.accept(this));
        }
        
        return st;
    }

    @Override
    public Object visit(PStatLocalVarDec node) {
        // PStatLocalVarDec_st(name, val, is_global) ::= <<
        ST st = m_stg.getInstanceOf("PStatLocalVarDec_st");
        if (!AuxSType.isVoid(node.m_name.getType())) {
            st.add("name", node.m_name.toStringMCIns());
        }

        if (null != node.m_val) {
            st.add("val", node.m_val.accept(this));
        }

        st.add("is_global", node.m_name.getSId().isGlobalValue());
        return st;
    }

    @Override
    public Object visit(PStatAssignment node) {
    	// PStatAssignment_st(name, val) ::= <<
        ST st = m_stg.getInstanceOf("PStatAssignment_st");
        if (!AuxSType.isVoid(node.m_name.getType())) {
        	st.add("name", node.m_name.toStringMCIns());
        }
        st.add("val", node.m_val.accept(this));
        return st;
    }

    @Override
    public Object visit(PExpAtom node) {
        return node.toStringMCIns();
    }

    @Override
    public Object visit(PProcAtom node) {
        ST st = m_stg.getInstanceOf("pprocatom_st");
        return st;
    }

    @Override
    public Object visit(PProcCall node) {
        ST st = m_stg.getInstanceOf("pproccall_st");
        st.add("name", node.m_name.toStringMCIns());
        return st;
        
    }

    @Override
    public Object visit(PExpID node) {
        Address addr = node.m_tid.getAddr();
        
        if (node.m_tid.getSId().isConstant()) {
        	String id = node.m_tid.toStringMCIns();
        	return AuxPats.convertConst(id);
        }
        if (node.m_tid.isThread()) {
            return addr.toStringMCIns();
        } else {
            return node.m_tid.toStringMCIns();
        }
        
    }

    @Override
    public Object visit(PGDecProc node) {
        ST st = m_stg.getInstanceOf("pgdecproc_st");
        st.add("name", node.m_name.toStringMCIns());
//        for (TID para: node.m_paraLst) {
//            st.add("para_lst", para.toStringMCIns());
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
        if (AuxSType.isBool(node.m_tid.getType())) {
            st = m_stg.getInstanceOf("pexpstackopr_bool_t");
        } else if (AuxSType.isInt(node.m_tid.getType())) {
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
        st.add("name", node.m_name.toStringMCIns());
        for (MCSId ele: node.m_eleLst) {
            st.add("ele_lst", ele.toStringMCIns());
        }
        
        return st;
    }

    @Override
    public Object visit(PChannelSend node) {
        ST st = m_stg.getInstanceOf("pchannelsend_st");
        st.add("name", node.m_name.toStringMCIns());
        for (PExp ele: node.m_msgLst) {
            st.add("msg_lst", ele.accept(this));
        }
        
        return st;
    }
//// {plus, minus, mul, div, gt, gte, lt, lte, eq, inc, dec};
//    @Override
//    public Object visit(PExpOpr node) {
//        ST st = null;
//        if (PExpOpr.isUnary(node.m_type)) {
//            st = m_stg.getInstanceOf("pexpopr_unary_st");
//            st.add("exp", node.m_opr1.accept(this));
//        } else {
//            st = m_stg.getInstanceOf("pexpopr_binary_st");
//            st.add("exp1", node.m_opr1.accept(this));
//            st.add("exp2", node.m_opr2.accept(this));
//        }
//        
//        st.add("opr", PExpOpr.stringType(node.m_type));
//        return st;
//    }

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


//    @Override
//    public Object visit(PExpTuple node) {
//        if (node != PExpTuple.cNone) {
//            throw new Error("not supported");
//        }
//        ST st = m_stg.getInstanceOf("pnone_st");
//        return st;
//        
//    }

//    @Override
//    public Object visit(PGDecArray node) {
//        // pgdecarray_st(id, sz) ::= <<
//        ST st = m_stg.getInstanceOf("pgdecarray_st");
//        st.add("id", node.m_tid);
//        st.add("sz", node.m_sz);
//        
//        return st;
//    }


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
    
    
	@Override
	public Object visit(PInsAtomRefCreate node) {
		// PInsAtomRefCreate_st(holder, init, is_global) ::= <<
		ST st = m_stg.getInstanceOf("PInsAtomRefCreate_st");
		st.add("holder", node.m_holder.toStringMCIns());
		st.add("init", node.m_v.accept(this));
		st.add("is_global", node.m_holder.getSId().isGlobalValue());
		return st;
	}



    @Override
    public Object visit(PInsAtomRefGet node) {
        // PInsAtomRefGet_st(holder, ref, is_global) ::= <<
        ST st = m_stg.getInstanceOf("PInsAtomRefGet_st");
        st.add("ref", node.m_globalVar.accept(this));
      
        st.add("holder", node.m_localHolder.toStringMCIns());
        st.add("is_global", node.m_localHolder.getSId().isGlobalValue()); 
        return st;        
        
    }


//    @Override
//    public Object visit(PInsLoadArray node) {
//        // pinsloadarray_st(src, index, dst, is_global) ::= <<
//        ST st = m_stg.getInstanceOf("pinsloadarray_st");
//        st.add("src", node.m_globalVar);
//        st.add("dst", node.m_localHolder);
//        st.add("index", node.m_localIndex.accept(this));
//        
//        if (node.m_localHolder.isGlobal()) {  // assign to a global value
//            st.add("is_global", true);
//        } else {
//            st.add("is_global", false);
//        }
//        
//        return st;
//    }


    @Override
    public Object visit(PInsAtomRefUpdate node) {
        // PInsAtomRefUpdate_st(ref, exp) ::= <<
        ST st = m_stg.getInstanceOf("PInsAtomRefUpdate_st");
        st.add("ref", node.m_globalVar.accept(this));
        st.add("exp", node.m_localSrc.accept(this));
        
        return st;
    }


//    @Override
//    public Object visit(PInsStoreArray node) {
//        // pinsstorearray_st(src, dst, index) ::= <<
//    	ST st = m_stg.getInstanceOf("pinsstorearray_st");
//    	st.add("src", node.m_localSrc.accept(this));
//    	st.add("dst", node.m_globalVar);
//    	st.add("index", node.m_localIndex.accept(this));
//    	
//    	return st;
//    }


    @Override
    public Object visit(PStatProcCallPrelogue node) {
        // PStatProcCallPrelogue_st(args, is_tail_call) ::= <<
        ST st = m_stg.getInstanceOf("PStatProcCallPrelogue_st");
        for (PExp exp: node.m_args) {
            st.add("args", exp.accept(this));
        }
        st.add("is_tail_call", node.m_isTailCall);
        
        return st;
    }


    // Tail call has no epilogue. (See MyCspInsTransformer.java.)
    // Therefore this cannot be a tail call.
    @Override
    public Object visit(PStatProcCallEpilogue node) {
        // PStatProcCallEpilogue_st(is_void, ret, is_global) ::= <<
		ST st = m_stg.getInstanceOf("PStatProcCallEpilogue_st");
		st.add("is_void", AuxSType.isVoid(node.m_ret.getType()));
		st.add("ret", node.m_ret.toStringMCIns());
		st.add("is_global", node.m_ret.getSId().isGlobalValue());
    	
    	return st;
    }


	@Override
    public Object visit(PProcThreadCreate node) {
	    // pprocthreadcreate_st(tid, funaddr, args) ::= <<
		ST st = m_stg.getInstanceOf("pprocthreadcreate_st");
		st.add("tid", node.m_tid.accept(this));
		st.add("funaddr", node.m_funlab.getAddr().toStringMCIns());
		st.add("args", node.m_args.accept(this));
		
		return st;
    }



    @Override
    public Object visit(PInsMutexCreate node) {
        // pinsmutexalloc_st(holder, is_global) ::= <<
    	ST st = m_stg.getInstanceOf("pinsmutexalloc_st");
    	st.add("holder", node.m_holder.toStringMCIns());
    	if (node.m_holder.getSId().isGlobalValue()) {
    		st.add("is_global", true);
    	} else {
    		st.add("is_global", false);
    	}
    	
    	return st;
    }

//	@Override
//    public Object visit(PInsMutexRelease node) {
//        // pinsmutexrelease_st(mutex) ::= <<
//    	ST st = m_stg.getInstanceOf("pinsmutexrelease_st");
//    	st.add("mutex", node.m_mutex.accept(this));
//    	
//    	return st;
//    }



//	@Override
//    public Object visit(PInsCondRelease node) {
//        // pinscondrelease_st(cond) ::= <<
//    	ST st = m_stg.getInstanceOf("pinscondrelease_st");
//    	st.add("cond", node.m_cond.accept(this));
//    	
//    	return st;
//    }


    @Override
    public Object visit(PInsMCAssert node) {
        // pinsmcassert_st(pred) ::= <<
        ST st = m_stg.getInstanceOf("pinsmcassert_st");
        st.add("pred", node.m_localSrc.accept(this));
        
        return st;
    }


	@Override
	public Object visit(PExpPatLabDecompose node) {
		// PExpPatLabDecompose_st(tup, index) ::= <<
		ST st = m_stg.getInstanceOf("PExpPatLabDecompose_st");
		st.add("tup", node.m_tup.accept(this));
		st.add("index", node.m_index);
		return st;
	}


	@Override
	public Object visit(PExpFormClosure node) {
		// PExpFormClosure_st(funaddr, envname) ::= <<
		ST st = m_stg.getInstanceOf("PExpFormClosure_st");
		 
		st.add("funaddr", node.m_funLab.getAddr().toStringMCIns());
		st.add("envname", node.m_env.accept(this));
		return st;
	}


	@Override
	public Object visit(PExpTupleCreate node) {
		// PExpTupleCreate_st(len) ::= <<
		ST st = m_stg.getInstanceOf("PExpTupleCreate_st");
		st.add("len", node.m_len);
		return st;
	}


	@Override
	public Object visit(PProcGrpMCAtomicStart node) {
		// PProcGrpMCAtomicStart_st(proc) ::= <<
        ST st = m_stg.getInstanceOf("PProcGrpMCAtomicStart_st");
        st.add("proc", node.m_proc.accept(this));
        return st;
	}
	
	@Override
	public Object visit(PProcGrpMCAtomicEnd node) {
		// PProcGrpMCAtomicEnd_st(proc) ::= <<
        ST st = m_stg.getInstanceOf("PProcGrpMCAtomicEnd_st");
        if (node.m_proc == PProcAtom.SKIP) {
        	
        } else {
        	st.add("proc", node.m_proc.accept(this));
        }
        
        return st;
	}


	@Override
	public Object visit(PInsMCGet node) {
		// PInsMCGet_st(holder, gv) ::= <<
		ST st = m_stg.getInstanceOf("PInsMCGet_st");
		st.add("holder", node.m_holder.toStringMCIns());
		st.add("gv", node.m_gv.toStringMCIns());
		return st;
	}


	@Override
	public Object visit(PInsMCSet node) {
		// PInsMCSet_st(gv, v) ::= <<
		ST st = m_stg.getInstanceOf("PInsMCSet_st");
		st.add("gv", node.m_gname.toStringMCIns());
		st.add("v", node.m_v.accept(this));
		return st;
	}


	@Override
	public Object visit(PInsTupleAdd node) {
		// PInsTupleAdd_st(tupname, index, e) ::= <<
		ST st = m_stg.getInstanceOf("PInsTupleAdd_st");
		st.add("tupname", node.m_tupname.toStringMCIns());
		st.add("index", node.m_ind);
		st.add("e", node.m_v.accept(this));
		return st;
	}


	@Override
	public Object visit(PInsMCVLockViewGet node) {
		// PInsMCVLockViewGet_st(args, holder) ::= <<
		ST st = m_stg.getInstanceOf("PInsMCVLockViewGet_st");
		for (PExp arg: node.m_args) {
			st.add("args", arg.accept(this));
		}
		st.add("holder", node.m_holder.toStringMCIns());
		return st;
	}
	

	@Override
	public Object visit(PInsMCVLockViewPut node) {
		// PInsMCVLockViewPut_st(v) ::= <<
		ST st = m_stg.getInstanceOf("PInsMCVLockViewPut_st");
		st.add("v", node.m_v.accept(this));
		return st;
	}


	@Override
	public Object visit(PInsArrayRefUpdate node) {
        // PInsArrayRefUpdate_st(ref, pos, exp) ::= <<
        ST st = m_stg.getInstanceOf("PInsArrayRefUpdate_st");
        st.add("ref", node.m_ref.accept(this));
        st.add("pos", node.m_pos.accept(this));
        st.add("exp", node.m_v.accept(this));
        
        return st;
	}


	@Override
	public Object visit(PInsArrayRefCreate node) {
		// PInsArrayRefCreate_st(holder, len, init, is_global) ::= <<
		ST st = m_stg.getInstanceOf("PInsArrayRefCreate_st");
		st.add("holder", node.m_holder.toStringMCIns());
		st.add("len", node.m_len.accept(this));
		st.add("init", node.m_v.accept(this));
		st.add("is_global", node.m_holder.getSId().isGlobalValue());
		return st;
	}


	@Override
	public Object visit(PInsArrayRefGet node) {
        // PInsArrayRefGet_st(holder, ref, pos, is_global) ::= <<
        ST st = m_stg.getInstanceOf("PInsArrayRefGet_st");
        st.add("ref", node.m_ref.accept(this));
        st.add("pos", node.m_pos.accept(this));
      
        st.add("holder", node.m_holder.toStringMCIns());
        st.add("is_global", node.m_holder.getSId().isGlobalValue()); 
        return st;
	}


	@Override
	public Object visit(PInsTIdAllocate node) {
        // PInsTIdAllocate_st(holder, is_global) ::= <<
    	ST st = m_stg.getInstanceOf("PInsTIdAllocate_st");
    	st.add("holder", node.m_holder.toStringMCIns());
    	if (node.m_holder.getSId().isGlobalValue()) {
    		st.add("is_global", true);
    	} else {
    		st.add("is_global", false);
    	}
    	
    	return st;
	}


	@Override
	public Object visit(PInsSharedCreate node) {
	    // PInsSharedCreate_st(holder, v, n, is_global) ::= <<
		ST st = m_stg.getInstanceOf("PInsSharedCreate_st");
		st.add("holder", node.m_holder.toStringMCIns());
		st.add("v", node.m_v.accept(this));
		st.add("n", node.m_n.accept(this));
		if (node.m_holder.getSId().isGlobalValue()) {
			st.add("is_global", true);
		} else {
			st.add("is_global", false);
		}
		
		return st;
	}


	@Override
	public Object visit(PNodeMCAtomicStart node) {
		throw new Error("Should not happen.");
	}


	@Override
	public Object visit(PNodeMCAtomicEnd node) {
		throw new Error("Should not happen.");
	}

}










