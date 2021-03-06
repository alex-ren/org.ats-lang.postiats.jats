package jats.utfpl.stfpl.pats;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import jats.utfpl.stfpl.mcinstruction.MCSId;
import jats.utfpl.stfpl.mycspinstructions.CIArrayRefCreate;
import jats.utfpl.stfpl.mycspinstructions.CIArrayRefGet;
import jats.utfpl.stfpl.mycspinstructions.CIArrayRefUpdate;
import jats.utfpl.stfpl.mycspinstructions.CIAtomRefCreate;
import jats.utfpl.stfpl.mycspinstructions.CIAtomRefGet;
import jats.utfpl.stfpl.mycspinstructions.CIAtomRefUpdate;
import jats.utfpl.stfpl.mycspinstructions.CICond;
import jats.utfpl.stfpl.mycspinstructions.CIFormClosure;
import jats.utfpl.stfpl.mycspinstructions.CIFormEnv;
import jats.utfpl.stfpl.mycspinstructions.CIFormTuple;
import jats.utfpl.stfpl.mycspinstructions.CIFunCall;
import jats.utfpl.stfpl.mycspinstructions.CIGetEleFromEnv;
import jats.utfpl.stfpl.mycspinstructions.CIMCAssert;
import jats.utfpl.stfpl.mycspinstructions.CIMCGet;
import jats.utfpl.stfpl.mycspinstructions.CIMCSet;
import jats.utfpl.stfpl.mycspinstructions.CIMCVLockViewGet;
import jats.utfpl.stfpl.mycspinstructions.CIMCVLockViewPut;
import jats.utfpl.stfpl.mycspinstructions.CIMove;
import jats.utfpl.stfpl.mycspinstructions.CIMutexCreate;
import jats.utfpl.stfpl.mycspinstructions.CIPatLabDecompose;
import jats.utfpl.stfpl.mycspinstructions.CIProcCallEpilog;
import jats.utfpl.stfpl.mycspinstructions.CIProcCallPrelogue;
import jats.utfpl.stfpl.mycspinstructions.CIReturn;
import jats.utfpl.stfpl.mycspinstructions.CISharedCreate;
import jats.utfpl.stfpl.mycspinstructions.CITIdAllocate;
import jats.utfpl.stfpl.mycspinstructions.CIVarDef;
import jats.utfpl.stfpl.mycspinstructions.FunctionMyCsp;
import jats.utfpl.stfpl.mycspinstructions.GrpCond;
import jats.utfpl.stfpl.mycspinstructions.GrpEvent;
import jats.utfpl.stfpl.mycspinstructions.GrpMCAtomicEnd;
import jats.utfpl.stfpl.mycspinstructions.GrpMCAtomicStart;
import jats.utfpl.stfpl.mycspinstructions.GrpProc;
import jats.utfpl.stfpl.mycspinstructions.GrpThreadCreate;
import jats.utfpl.stfpl.mycspinstructions.IMyCspInsVisitor;
import jats.utfpl.stfpl.mycspinstructions.IMyCspTemp;
import jats.utfpl.stfpl.mycspinstructions.MyCspGroup;
import jats.utfpl.stfpl.mycspinstructions.MyCspInstruction;
import jats.utfpl.stfpl.mycspinstructions.MyCspTempID;
import jats.utfpl.stfpl.mycspinstructions.MyCspTempVal;
import jats.utfpl.stfpl.mycspinstructions.ProgramMyCspIns;
import jats.utfpl.stfpl.mycspinstructions.VariableInfo;
import jats.utfpl.stfpl.stype.AuxSType;
import jats.utfpl.utils.Log;

/*
 * Handle:
 * 1. Get from stack
 * 2. PUsh to stack
 */
public class PatCspsTransformer implements IMyCspInsVisitor {
	 
	private ProgramMyCspIns m_prog;
	
	public PatCspsTransformer(ProgramMyCspIns prog) {
		m_prog = prog;
	}
	
    public PModel transform() {

        List<PGDec> gvlst = new ArrayList<PGDec>();
        for (VariableInfo gv: m_prog.m_globalVars) {
//        	ISType ty = gv.getMCSId().getType();
//            if (ty instanceof PATTypeArray) {
//                PGDecArray pgarr = new PGDecArray(gv.getTID(), ((PATTypeArray)ty).getSize());
//                gvlst.add(pgarr);
//            } else {
                PGDecVar pgv = new PGDecVar(gv.getMCSId()); // , PExpAtom.createFromInt(0));
                gvlst.add(pgv);
//            }
        }
        
        PProc mainBody = CBlockLst2PProc(m_prog.m_main);
        
        List<PGDecProc> procLst = new ArrayList<PGDecProc>();
        for (FunctionMyCsp proc: m_prog.m_procLst) {
            procLst.add(transFunc(proc));
        }
        
        PModel model = new PModel(gvlst, mainBody, procLst, m_prog.m_extCodeLst);
//        model.complete();
        
        return model;
        
    }
    
    private PGDecProc transFunc(FunctionMyCsp func) {

        List<MCSId> paraLst = new ArrayList<MCSId>();
        for (MyCspTempID mid: func.m_paras) {
            paraLst.add(mid.getMCSId());
        }
        PProc body = CBlockLst2PProc(func.m_body);
        return new PGDecProc(func.m_name, paraLst, body);

    }

    private PProc CBlockLst2PProc(List<MyCspGroup> blkLst) {
        PProc retProc = PProcAtom.SKIP;
        
        ListIterator<MyCspGroup> iterator = blkLst.listIterator(blkLst.size());
        // Be careful, extra Skip in the end may be harmful.
        if (iterator.hasPrevious()) {
        	MyCspGroup cb = iterator.previous();
            Object evt_proc = cb.accept(this);
            if (evt_proc instanceof PProc) {
                retProc = (PProc) evt_proc;
            } else if (evt_proc instanceof PNodeMCAtomicStart) {
            	throw new Error("Should not happen.");
            } else if (evt_proc instanceof PNodeMCAtomicEnd) {
                retProc = new PProcGrpMCAtomicEnd(retProc);                
            } else if (evt_proc instanceof PNodeEvent){
                retProc = new PProcEvent((PNodeEvent)evt_proc, PProcAtom.SKIP);
            } else {
            	throw new Error(evt_proc + " is not supported.");
            }
        }
        for (; iterator.hasPrevious();) {
            final MyCspGroup cb = iterator.previous();
            Object evt_proc = cb.accept(this);
            if (evt_proc instanceof PProc) {
                retProc = new PProcSeq((PProc)evt_proc, retProc);
            } else if (evt_proc instanceof PNodeMCAtomicStart) {
            	retProc = new PProcGrpMCAtomicStart(retProc);
            } else if (evt_proc instanceof PNodeMCAtomicEnd) {
                retProc = new PProcGrpMCAtomicEnd(retProc);
            } else if (evt_proc instanceof PNodeEvent) {
                retProc = new PProcEvent((PNodeEvent)evt_proc, retProc);
            } else {
            	throw new Error(evt_proc + " is not supported.");
            }
        }
        
        return retProc;
    }
    
    /* **************** ****************** */
    // Auxiliary functions
	
	private void handleDefForNoEffectIns(List<PStat> stats, MyCspTempID holder, PExp exp) {
        if (holder.isDefinition()) {
        	stats.add(new PStatLocalVarDec(holder.getMCSId(), exp));
            if (holder.isEscaped()) {
            	if (true == AuxSType.isVoid(holder.getType())) {
            		throw new Error("This is not allowed.");
            	}
            	stats.add(new PStatStackPush(new PExpID(holder.getMCSId())));
            }          
        } else {
            stats.add(new PStatAssignment(holder.getMCSId(), exp));  // The situation is caused by the holder of CICond.
        }
	}

    /* ***************** ****************** */
    // Handle Values
    
    private List<PExp> CTempList2PExpList(List<IMyCspTemp> lst) {
        List<PExp> ret = new ArrayList<PExp>();
        for (IMyCspTemp ele: lst) {
            ret.add((PExp)ele.accept(this));
        }
        return ret;
    }
    
//    private PExpID CTempId2PExpID(MyCspTempID id) {
//    	return (PExpID)(id.accept(this));
//    }
//    
    private PExp CTemp2PExp(IMyCspTemp id) {
    	return (PExp)(id.accept(this));
    }
    
    private List<PStat> CInsLst2PStatLst(List<MyCspInstruction> insLst) {
        List<PStat> statLst = new ArrayList<PStat>();
        for (MyCspInstruction ins: insLst) {
            @SuppressWarnings("unchecked")
            List<PStat> stats = (List<PStat>)ins.accept(this);
            statLst.addAll(stats);
        }
        return statLst;
    }
    
    @Override
    public PExp visit(MyCspTempID v) {

        if (v.isOutofScope()) {
        	if (v.getStackInfo() == null) {
        		Log.log4j.info("v is " + v.getMCSId().toStringMCIns() + " @ " + v);
        		throw new Error("Should not happen. check process function");
        	}
            return new PExpStackGet(v.getStackInfo().getOffset(), v.getMCSId());
        } else {
            return new PExpID(v.getMCSId());
        }
    }

    @Override
    public PExp visit(MyCspTempVal v) {
    	return PExpAtom.createFromAtomValue(v.getMCAtomValue());
    }
    
    /* ***************** ****************** */
    // Handle groups
    
    @Override
    public PProcBranch visit(GrpCond blk) {
        PExp condExp = CTemp2PExp(blk.m_cond);
        PProc ifProc = CBlockLst2PProc(blk.m_tb);
        PProc elseProc = CBlockLst2PProc(blk.m_fb);
        
        return new PProcBranch(condExp, ifProc, elseProc, PProcBranch.Type.ifcommon);
    }
    
    @Override
    public PNodeEvent visit(GrpEvent blk) {
        List<PStat> statLst =  CInsLst2PStatLst(blk.m_inslst);

        return new PNodeEvent(statLst, blk.m_funname, blk.m_no);
    }

    @Override
    public PProcCall visit(GrpProc blk) {
        return new PProcCall(blk.m_funlab, blk.m_isTail);
    }
    
    /* ***************** ****************** */
    // Handle instructions
    
    @Override
    public List<PStat> visit(CIMove ins) {  // no effect
        MCSId var = ins.m_holder.getMCSId();
        PExp exp = CTemp2PExp(ins.m_vp);
        
        List<PStat> ret = new ArrayList<PStat>();
        
        if (ins.m_holder.isDefinition()) {
            ret.add(new PStatLocalVarDec(var, exp));
            if (ins.m_holder.isEscaped()) {
                ret.add(new PStatStackPush(new PExpID(var)));
            }
            return ret;
        } else {
            ret.add(new PStatAssignment(var, exp));  // The situation is caused by the holder of CICond.
            return ret;
        }
    }

    @Override
    public List<PStat> visit(CIFunCall ins) {  // no effect
//    	System.out.println("===== PatCspsTransformer CIFunCall fun is " + ins.m_funlab.toStringMCIns());
        List<PExp> argLst = CTempList2PExpList(ins.m_args);
        PExpFuncCall exp = new PExpFuncCall(ins.m_funlab, argLst);
        
        List<PStat> ret = new ArrayList<PStat>();

        handleDefForNoEffectIns(ret, ins.m_ret, exp);
        
        return ret;
    }

    @Override
    public List<PStat> visit(CIReturn node) {  // has effect
        List<PStat> ret = new ArrayList<PStat>();
        ret.add(new PStatReturn(CTemp2PExp(node.m_v)));
        return ret;
    }

    @Override
    public List<PStat> visit(CIVarDef node) {  // no effect
        List<PStat> ret = new ArrayList<PStat>();
        ret.add(new PStatLocalVarDec(node.m_id.getMCSId(), null));
        
        return ret;
    }


	
	@Override
	public Object visit(CIAtomRefCreate node) {
        List<PStat> ret = new ArrayList<PStat>();
        MCSId holder = node.m_holder.getMCSId();
        
        PExp vp  = CTemp2PExp(node.m_vp);
        
        ret.add(new PInsAtomRefCreate(holder, vp));
        if (node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getMCSId())));
        }

        return ret;
	}
	
    @Override
    public List<PStat> visit(CIAtomRefGet node) {  // has effect
        List<PStat> ret = new ArrayList<PStat>();

        PExp ref = CTemp2PExp(node.m_ref);
        MCSId localHolder = node.m_localHolder.getMCSId();
        
        ret.add(new PInsAtomRefGet(ref, localHolder));
        
        if (node.m_localHolder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_localHolder.getMCSId())));
        }
        return ret;
        
    }

//    @Override
//    public Object visit(CILoadArray node) {
//        List<PStat> ret = new ArrayList<PStat>();
//        
//        TID globalVar = node.m_globalVar.getTID();
//        TID localHolder = node.m_localHolder.getTID();
//        PExp localIndex = (PExp)(node.m_localIndex.accept(this));
//        
//        ret.add(new PInsLoadArray(globalVar, localIndex, localHolder));
//        if (node.m_localHolder.isEscaped()) {
//            ret.add(new PStatStackPush(new PExpID(node.m_localHolder.getTID())));
//        }
//        
//        return ret;
//    }


    @Override
    public List<PStat> visit(CIAtomRefUpdate node) {  // has effect
        List<PStat> ret = new ArrayList<PStat>();
//        MCSId globalVar = node.m_globalVar.getMCSId();
        
        PExp ref = CTemp2PExp(node.m_ref);
        PExp localSrc  = CTemp2PExp(node.m_localSrc);
        
        ret.add(new PInsAtomRefUpdate(localSrc, ref));
        
        return ret;
    }

//    @Override
//    public Object visit(CIStoreArray node) {
//        List<PStat> ret = new ArrayList<PStat>();
//        TID globalVar = node.m_globalVar.getTID();
//        PExp localSrc = (PExp)node.m_localSrc.accept(this);
//        PExp localIndex = (PExp)node.m_localIndex.accept(this);
//        
//        ret.add(new PInsStoreArray(localSrc, globalVar, localIndex));
//        
//        return ret;
//    }

    @Override
    public Object visit(CICond node) {  // no effect
        List<PStat> ret = new ArrayList<PStat>();
        List<PStat> trueBranch = CInsLst2PStatLst(node.m_true);
        List<PStat> falseBranch = CInsLst2PStatLst(node.m_false);
        PExp cond = (PExp)node.m_cond.accept(this);
        
        ret.add(new PInsCond(cond, trueBranch, falseBranch));
        if (null != node.m_holder && node.m_holder.isDefinition() && node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getMCSId())));
        }
        return ret;
    }

    @Override
    public Object visit(CIProcCallPrelogue node) {
        List<PStat> ret = new ArrayList<PStat>();
        
        List<PExp> args = CTempList2PExpList(node.m_args);
        ret.add(new PStatProcCallPrelogue(args, node.m_isTail));
        
        return ret;
    }

    @Override
    public Object visit(CIProcCallEpilog node) {
        List<PStat> ret = new ArrayList<PStat>();
        
        MCSId funlab = node.m_funlab;
        MCSId retHolder = node.m_holder.getMCSId();
        ret.add(new PStatProcCallEpilogue(funlab, retHolder));
        
        if (node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getMCSId())));
        }
        
        return ret;
    }

	@Override
    public Object visit(GrpThreadCreate node) {
		PExp tid = CTemp2PExp(node.m_tid);
		PExp args = CTemp2PExp(node.m_args);
		PProcThreadCreate proc = new PProcThreadCreate(tid, node.m_funlab, args);
		
		return proc;
    }

    @Override
    public Object visit(CIMutexCreate node) {
        List<PStat> ret = new ArrayList<PStat>();
        MCSId holder = node.m_holder.getMCSId();
        
        ret.add(new PInsMutexCreate(holder));
        if (node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getMCSId())));
        }

        return ret;
    }
    
//	@Override
//    public Object visit(CIMutexRelease node) {
//        List<PStat> ret = new ArrayList<PStat>();
//        
//	    PExp mutex = (PExp)node.m_mutex.accept(this);
//	    
//	    PInsMutexRelease nIns = new PInsMutexRelease(mutex);
//	    ret.add(nIns);
//	    return ret;
//    }
	
    
//	@Override
//    public Object visit(CICondRelease node) {
//        List<PStat> ret = new ArrayList<PStat>();
//        
//	    PExp cond = (PExp)node.m_cond.accept(this);
//	    
//	    PInsCondRelease nIns = new PInsCondRelease(cond);
//	    ret.add(nIns);
//	    return ret;
//    }

    @Override
    public Object visit(CIMCAssert node) {
        List<PStat> ret = new ArrayList<PStat>();
        PExp localSrc = CTemp2PExp(node.m_localSrc);
        
        ret.add(new PInsMCAssert(localSrc));
        
        return ret;
    }

	@Override
	public PNodeMCAtomicStart visit(GrpMCAtomicStart node) {
		PNodeMCAtomicStart proc = new PNodeMCAtomicStart();
		
		return proc;
	}
	

	@Override
	public PNodeMCAtomicEnd visit(GrpMCAtomicEnd node) {
		PNodeMCAtomicEnd proc = new PNodeMCAtomicEnd();
		
		return proc;
	}

	@Override
	public Object visit(CIMCGet node) {
		List<PStat> ret = new ArrayList<PStat>();
		MCSId globalVar = node.m_globalVar.getMCSId();
		MCSId localHolder = node.m_localHolder.getMCSId();
		ret.add(new PInsMCGet(globalVar, localHolder));

        if (node.m_localHolder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_localHolder.getMCSId())));
        }
        return ret;
		
	}

	@Override
	public Object visit(CIMCSet node) {
		List<PStat> ret = new ArrayList<PStat>();
		
		MCSId gname = node.m_globalVar.getMCSId();
		PExp v = CTemp2PExp(node.m_localSrc);
		ret.add(new PInsMCSet(gname, v));

        return ret;
	}

	@Override
	public Object visit(CIFormTuple ins) {
		List<PStat> ret = new ArrayList<PStat>();

		PExpTupleCreate ecreate = new PExpTupleCreate(ins.m_eles.size());

		handleDefForNoEffectIns(ret, ins.m_holder, ecreate);
		
		MCSId tupname = ins.m_holder.getMCSId();
		int index = 0;
		for (IMyCspTemp arg: ins.m_eles) {
			PExp ele = CTemp2PExp(arg);
			PInsTupleAdd ins_add = new PInsTupleAdd(tupname, ele, index);
			ret.add(ins_add);
			++index;
		}
		
		return ret;
	}

	@Override
	public Object visit(CIFormEnv ins) {
		// Do the same as CIFormTuple
		List<PStat> ret = new ArrayList<PStat>();

		PExpTupleCreate ecreate = new PExpTupleCreate(ins.m_eles.size());

		handleDefForNoEffectIns(ret, ins.m_holder, ecreate);
		
		MCSId tupname = ins.m_holder.getMCSId();
		int index = 0;
		for (IMyCspTemp arg: ins.m_eles) {
			PExp ele = CTemp2PExp(arg);
			PInsTupleAdd ins_add = new PInsTupleAdd(tupname, ele, index);
			ret.add(ins_add);
			++index;
		}
		
		return ret;
	}

	@Override
	public Object visit(CIPatLabDecompose ins) {
		List<PStat> ret = new ArrayList<PStat>();

		PExp tup = CTemp2PExp(ins.m_record);
		PExpPatLabDecompose exp = new PExpPatLabDecompose(ins.m_index, tup);
		
		handleDefForNoEffectIns(ret, ins.m_holder, exp);
		
		return ret;
	}

	@Override
	public Object visit(CIGetEleFromEnv ins) {
		// Do the same as CIPatLabDecompose
		List<PStat> ret = new ArrayList<PStat>();

		PExp tup = CTemp2PExp(ins.m_env);
		PExpPatLabDecompose exp = new PExpPatLabDecompose(ins.m_index, tup);
		
		handleDefForNoEffectIns(ret, ins.m_holder, exp);
		
		return ret;
	}

	@Override
	public Object visit(CIFormClosure ins) {
		List<PStat> ret = new ArrayList<PStat>();

		PExp env = CTemp2PExp(ins.m_env_name);
		PExpFormClosure exp = new PExpFormClosure(ins.m_fun_name.getMCSId(), env);

		handleDefForNoEffectIns(ret, ins.m_holder, exp);
		
		return ret;
	}



	@Override
	public Object visit(CIMCVLockViewGet node) {
        List<PStat> ret = new ArrayList<PStat>();
        
        List<PExp> args = CTempList2PExpList(node.m_args);
        MCSId holder = node.m_holder.getMCSId();
        ret.add(new PInsMCVLockViewGet(args, holder));
        
        if (node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getMCSId())));
        }

        return ret;
	}
	
	@Override
	public Object visit(CIMCVLockViewPut node) {
		List<PStat> ret = new ArrayList<PStat>();
		
		PExp v = CTemp2PExp(node.m_v);
		ret.add(new PInsMCVLockViewPut(v));

        return ret;
	}
	

	@Override
	public Object visit(CIArrayRefUpdate node) {
        List<PStat> ret = new ArrayList<PStat>();
      
      PExp ref = CTemp2PExp(node.m_ref);
      PExp pos = CTemp2PExp(node.m_pos);
      PExp v   = CTemp2PExp(node.m_v);
      
      ret.add(new PInsArrayRefUpdate(ref, pos, v));
      
      return ret;
	}

	@Override
	public Object visit(CIArrayRefCreate node) {
        List<PStat> ret = new ArrayList<PStat>();
        MCSId holder = node.m_holder.getMCSId();
        
        PExp len  = CTemp2PExp(node.m_len);
        PExp v  = CTemp2PExp(node.m_v);
        
        ret.add(new PInsArrayRefCreate(holder, len, v));
        if (node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getMCSId())));
        }

        return ret;
	}

	@Override
	public Object visit(CIArrayRefGet node) {
        List<PStat> ret = new ArrayList<PStat>();

        PExp ref = CTemp2PExp(node.m_ref);
        PExp pos = CTemp2PExp(node.m_pos);
        MCSId holder = node.m_holder.getMCSId();
        
        ret.add(new PInsArrayRefGet(ref, pos, holder));
        
        if (node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getMCSId())));
        }
        return ret;
	}

	@Override
	public Object visit(CITIdAllocate node) {
        List<PStat> ret = new ArrayList<PStat>();
        MCSId holder = node.m_holder.getMCSId();
        
        ret.add(new PInsTIdAllocate(holder));
        if (node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getMCSId())));
        }

        return ret;
	} 

	@Override
	public Object visit(CISharedCreate node) {
		
        List<PStat> ret = new ArrayList<PStat>();
        MCSId holder = node.m_holder.getMCSId();
        PExp v = CTemp2PExp(node.m_vp);
        PExp n = CTemp2PExp(node.m_n_cond);
        
        ret.add(new PInsSharedCreate(holder, v, n));
        if (node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getMCSId())));
        }

        return ret;
	}
	
}










