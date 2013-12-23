package jats.utfpl.patcsps;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import jats.utfpl.csps.CBThreadCreate;
import jats.utfpl.csps.CBlock;
import jats.utfpl.csps.CBCond;
import jats.utfpl.csps.CBEvent;
import jats.utfpl.csps.CICond;
import jats.utfpl.csps.CICondAlloc;
import jats.utfpl.csps.CICondRelease;
import jats.utfpl.csps.CIFunCall;
import jats.utfpl.csps.CILoad;
import jats.utfpl.csps.CILoadArray;
import jats.utfpl.csps.CIMove;
import jats.utfpl.csps.CIMutexAlloc;
import jats.utfpl.csps.CIMutexRelease;
import jats.utfpl.csps.CIProcCallEpilog;
import jats.utfpl.csps.CIProcCallPrelogue;
import jats.utfpl.csps.CIStore;
import jats.utfpl.csps.CIStoreArray;
import jats.utfpl.csps.CIVarDef;
import jats.utfpl.csps.FunctionCSPS;
import jats.utfpl.csps.CIReturn;
import jats.utfpl.csps.CInstruction;
import jats.utfpl.csps.CBProc;
import jats.utfpl.csps.CSPSVisitor;
import jats.utfpl.csps.CTemp;
import jats.utfpl.csps.CTempID;
import jats.utfpl.csps.CTempVal;
import jats.utfpl.csps.ProgramCSPS;
import jats.utfpl.csps.VariableInfo;
import jats.utfpl.instruction.TID;
import jats.utfpl.instruction.TupleValue;
import jats.utfpl.patcsps.type.PATType;
import jats.utfpl.patcsps.type.PATTypeArray;

public class PatCspsTransformer implements CSPSVisitor {
    public PModel transProg(ProgramCSPS prog) {

        List<PGDec> gvlst = new ArrayList<PGDec>();
        for (VariableInfo gv: prog.m_globalVars) {
            PATType ty = gv.getTID().getType();
            if (ty instanceof PATTypeArray) {
                PGDecArray pgarr = new PGDecArray(gv.getTID(), ((PATTypeArray)ty).getSize());
                gvlst.add(pgarr);
            } else {
                PGDecVar pgv = PGDecVar.createInit(gv.getTID(), PExpAtom.createFromInt(0));
                gvlst.add(pgv);
            }
        }
        
        PProc mainBody = CBlockLst2PProc(prog.m_main);
        
        List<PGDecProc> procLst = new ArrayList<PGDecProc>();
        for (FunctionCSPS proc: prog.m_procLst) {
            procLst.add(transFunc(proc));
        }
        
        PModel model = new PModel(gvlst, mainBody, procLst);
//        model.complete();
        
        return model;
        
    }
    
    private PGDecProc transFunc(FunctionCSPS func) {

        List<TID> paraLst = new ArrayList<TID>();
        for (CTempID tid: func.m_paras) {
            paraLst.add(tid.getTID());
        }
        PProc body = CBlockLst2PProc(func.m_body);
        return new PGDecProc(func.m_name, paraLst, body);

    }

    private PProc CBlockLst2PProc(List<CBlock> blkLst) {
        PProc retProc = PProcAtom.SKIP;
        
        ListIterator<CBlock> iterator = blkLst.listIterator(blkLst.size());
        // Be careful, extra Skip in the end may be harmful.
        if (iterator.hasPrevious()) {
            CBlock cb = iterator.previous();
            Object evt_proc = cb.accept(this);
            if (evt_proc instanceof PProc) {
                retProc = (PProc) evt_proc;
            } else {
                retProc = new PProcEvent((PEvent)evt_proc, PProcAtom.SKIP);
            }
        }
        for (; iterator.hasPrevious();) {
            final CBlock cb = iterator.previous();
            Object evt_proc = cb.accept(this);
            if (evt_proc instanceof PProc) {
                retProc = new PProcSeq((PProc)evt_proc, retProc);
            } else {
                retProc = new PProcEvent((PEvent)evt_proc, retProc);
            }
        }
        
        return retProc;
    }

    private List<PExp> CTempList2PExpList(List<CTemp> lst) {
        List<PExp> ret = new ArrayList<PExp>();
        for (CTemp ele: lst) {
            ret.add((PExp)ele.accept(this));
        }
        return ret;
    }
    

    private List<PStat> CInsLst2PStatLst(List<CInstruction> insLst) {
        List<PStat> statLst = new ArrayList<PStat>();
        for (CInstruction ins: insLst) {
            @SuppressWarnings("unchecked")
            List<PStat> stats = (List<PStat>)ins.accept(this);
            statLst.addAll(stats);
        }
        return statLst;
    }

    @Override
    public PProcBranch visit(CBCond blk) {
        PExp condExp = (PExp)blk.m_cond.accept(this);
        PProc ifProc = CBlockLst2PProc(blk.m_tb);
        PProc elseProc = CBlockLst2PProc(blk.m_fb);
        
        return new PProcBranch(condExp, ifProc, elseProc, PProcBranch.Type.ifcommon);
    }
    
    @Override
    public PEvent visit(CBEvent blk) {
        List<PStat> statLst =  CInsLst2PStatLst(blk.m_inslst);

        return new PEvent(statLst);
    }

    @Override
    public PProcCall visit(CBProc blk) {
        return new PProcCall(blk.m_funlab, blk.m_isTail);
    }

    @Override
    public List<PStat> visit(CIMove ins) {
        TID var = ins.m_holder.getTID();
        PExp exp = (PExp)(ins.m_vp.accept(this));
        
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
    public List<PStat> visit(CIFunCall ins) {
        List<PExp> argLst = CTempList2PExpList(ins.m_args);
        PExpFuncCall exp = new PExpFuncCall(ins.m_funlab, argLst);
        
        List<PStat> ret = new ArrayList<PStat>();
        
        TID retName = ins.m_ret.getTID();
        if (ins.m_ret.isDefinition()) {
            ret.add(new PStatLocalVarDec(retName, exp));
            if (ins.m_ret.isEscaped()) {
                ret.add(new PStatStackPush(new PExpID(retName)));
            }
            
            return ret;            
        } else {
            ret.add(new PStatAssignment(ins.m_ret.getTID(), exp));  // The situation is caused by the holder of CICond.
            return ret;
        }
    }

    @Override
    public List<PStat> visit(CIReturn node) {
        List<PStat> ret = new ArrayList<PStat>();
        ret.add(new PStatReturn((PExp)node.m_v.accept(this)));
        return ret;
    }


    @Override
    public PExp visit(CTempID v) {

        if (v.isOutofScope()) {
            return new PExpStackGet(v.getStackInfo().getOffset(), v.getTID());
        } else {
            return new PExpID(v.getTID());
        }
    }

    @Override
    public PExp visit(CTempVal v) {
        switch (v.m_type)
        {
        case atom:
            return PExpAtom.createFromAtomValue(v.getAtomValue());
        case tuple:
            if (v.getTupleValue() == TupleValue.cNone) {
                return PExpTuple.cNone;
            } else {
                throw new Error("not supported");
            }
        default:
            throw new Error("should not happen");
        }
    }

    @Override
    public Object visit(CIVarDef node) {
        List<PStat> ret = new ArrayList<PStat>();
        ret.add(new PStatLocalVarDec(node.m_id.getTID(), PExpTuple.cNone));
        if (node.m_id.isEscaped()) {
            throw new Error("This should not happen.");
        }
        
        return ret;
    }

    @Override
    public Object visit(CILoad node) {
        List<PStat> ret = new ArrayList<PStat>();
        
        TID globalVar = node.m_globalVar.getTID();
        TID localHolder = node.m_localHolder.getTID();
        
        ret.add(new PInsLoad(globalVar, localHolder));
        if (node.m_localHolder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_localHolder.getTID())));
        }
        return ret;
        
    }

    @Override
    public Object visit(CILoadArray node) {
        List<PStat> ret = new ArrayList<PStat>();
        
        TID globalVar = node.m_globalVar.getTID();
        TID localHolder = node.m_localHolder.getTID();
        PExp localIndex = (PExp)(node.m_localIndex.accept(this));
        
        ret.add(new PInsLoadArray(globalVar, localIndex, localHolder));
        if (node.m_localHolder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_localHolder.getTID())));
        }
        
        return ret;
    }


    @Override
    public Object visit(CIStore node) {
        List<PStat> ret = new ArrayList<PStat>();
        TID globalVar = node.m_globalVar.getTID();
        PExp localSrc = (PExp)node.m_localSrc.accept(this);
        
        ret.add(new PInsStore(localSrc, globalVar));
        
        return ret;
    }

    @Override
    public Object visit(CIStoreArray node) {
        List<PStat> ret = new ArrayList<PStat>();
        TID globalVar = node.m_globalVar.getTID();
        PExp localSrc = (PExp)node.m_localSrc.accept(this);
        PExp localIndex = (PExp)node.m_localIndex.accept(this);
        
        ret.add(new PInsStoreArray(localSrc, globalVar, localIndex));
        
        return ret;
    }

    @Override
    public Object visit(CICond node) {
        List<PStat> ret = new ArrayList<PStat>();
        List<PStat> trueBranch = CInsLst2PStatLst(node.m_true);
        List<PStat> falseBranch = CInsLst2PStatLst(node.m_false);
        PExp cond = (PExp)node.m_cond.accept(this);
        
        ret.add(new PInsCond(cond, trueBranch, falseBranch));
        if (null != node.m_holder && node.m_holder.isDefinition() && node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getTID())));
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
        
        TID funlab = node.m_funlab;
        TID retHolder = node.m_holder.getTID();
        ret.add(new PStatProcCallEpilogue(funlab, retHolder));
        
        if (node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getTID())));
        }
        
        return ret;
    }

	@Override
    public Object visit(CBThreadCreate node) {
		PExp tid = (PExp)node.m_tid.accept(this);
		PExp args = (PExp)node.m_args.accept(this);
		PProcThreadCreate proc = new PProcThreadCreate(tid, node.m_funlab, args);
		
		return proc;
    }

    @Override
    public Object visit(CIMutexAlloc node) {
        List<PStat> ret = new ArrayList<PStat>();
        TID holder = node.m_holder.getTID();
        
        ret.add(new PInsMutexAlloc(holder));
        if (node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getTID())));
        }

        return ret;
    }
	@Override
    public Object visit(CIMutexRelease node) {
        List<PStat> ret = new ArrayList<PStat>();
        
	    PExp mutex = (PExp)node.m_mutex.accept(this);
	    
	    PInsMutexRelease nIns = new PInsMutexRelease(mutex);
	    ret.add(nIns);
	    return ret;
    }
	

    @Override
    public Object visit(CICondAlloc node) {
        List<PStat> ret = new ArrayList<PStat>();
        TID holder = node.m_holder.getTID();
        
        ret.add(new PInsCondAlloc(holder));
        if (node.m_holder.isEscaped()) {
            ret.add(new PStatStackPush(new PExpID(node.m_holder.getTID())));
        }

        return ret;
    }
    
	@Override
    public Object visit(CICondRelease node) {
        List<PStat> ret = new ArrayList<PStat>();
        
	    PExp cond = (PExp)node.m_cond.accept(this);
	    
	    PInsCondRelease nIns = new PInsCondRelease(cond);
	    ret.add(nIns);
	    return ret;
    }
	
}










