package jats.utfpl.patcsps;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import jats.utfpl.csps.CBlock;
import jats.utfpl.csps.CCondBlock;
import jats.utfpl.csps.CEventBlock;
import jats.utfpl.csps.CIFunCall;
import jats.utfpl.csps.CIMove;
import jats.utfpl.csps.CIProcessDef;
import jats.utfpl.csps.CInstruction;
import jats.utfpl.csps.CProcessCallBlock;
import jats.utfpl.csps.CSPSVisitor;
import jats.utfpl.csps.CTemp;
import jats.utfpl.csps.CTempID;
import jats.utfpl.csps.CTempVal;
import jats.utfpl.csps.ProgramCSPS;
import jats.utfpl.csps.VariableInfo;
import jats.utfpl.instruction.TID;

public class PatCspsTransformer implements CSPSVisitor {
    public PModel trans(ProgramCSPS prog) {
        return (PModel)prog.accept(this);
    }
    
    
    private PProc CBlockLst2PProc(List<CBlock> blkLst) {
        PProc retProc = PProcAtom.SKIP;
        
        ListIterator<CBlock> iterator = blkLst.listIterator(blkLst.size());
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

    @Override
    public PProcBranch visit(CCondBlock blk) {
        PExp condExp = (PExp)blk.m_cond.accept(this);
        PProc ifProc = CBlockLst2PProc(blk.m_tb);
        PProc elseProc = CBlockLst2PProc(blk.m_fb);
        
        return new PProcBranch(condExp, ifProc, elseProc, PProcBranch.Type.ifcommon);
    }

    @Override
    public PEvent visit(CEventBlock blk) {
        List<PStat> statLst = new ArrayList<PStat>();
        for (CInstruction ins: blk.m_inslst) {
            PStat stat = (PStat)ins.accept(this);
            statLst.add(stat);            
        }
        return new PEvent(statLst);
    }

    @Override
    public PProcCall visit(CProcessCallBlock blk) {
        PProcCall ret = new PProcCall(blk.m_funlab, CTempList2PExpList(blk.m_args));
        return ret;
    }

    @Override
    public Object visit(CIMove ins) {
        TID var = ins.m_holder.getTID();
        PExp exp = (PExp)ins.m_vp.accept(this);
        
        if (ins.m_holder.isDefinition()) {
            return new PStatLocalVarDec(var, exp);
        } else {
            return new PStatAssignment(var, exp);
        }
    }

    @Override
    public Object visit(CIFunCall ins) {
        List<PExp> argLst = CTempList2PExpList(ins.m_args);
        PExpFuncCall exp = new PExpFuncCall(ins.m_funlab, argLst);
        
        if (ins.m_ret.isDefinition()) {
            return new PStatLocalVarDec(ins.m_ret.getTID(), exp);            
        } else {
            return new PStatAssignment(ins.m_ret.getTID(), exp);
        }
    }

    @Override
    public Object visit(CIProcessDef proc) {
        List<TID> paraLst = new ArrayList<TID>();
        for (CTempID tid: proc.m_paras) {
            paraLst.add(tid.getTID());
        }
        PProc body = CBlockLst2PProc(proc.m_body);
        return new PGDecProc(proc.m_name, paraLst, body);

    }

    @Override
    public Object visit(CTempID v) {
        if (v.isOutofScope()) {
            return new PExpStackOpr(v.getStackInfo().getFrame(), v.getStackInfo().getOffset());
        } else {
            return new PExpID(v.getTID());
        }
    }

    @Override
    public PExpAtom visit(CTempVal v) {
        return new PExpAtom(v.m_v);
    }

    @Override
    public Object visit(ProgramCSPS prog) {
        List<PGDecVar> gvlst = new ArrayList<PGDecVar>();
        for (VariableInfo gv: prog.m_globalVars) {
            PGDecVar pgv = new PGDecVar(gv);
            gvlst.add(pgv);            
        }
        
        PProc mainBody = CBlockLst2PProc(prog.m_main);
        
        PGDecProc mainProc = new PGDecProc(TID.MAIN, new ArrayList<TID>(), mainBody);
        
        List<PGDecProc> procLst = new ArrayList<PGDecProc>();
        for (CIProcessDef proc: prog.m_procLst) {
            procLst.add((PGDecProc)proc.accept(this));
        }
        
        return new PModel(gvlst, mainProc, procLst);
    }

}








