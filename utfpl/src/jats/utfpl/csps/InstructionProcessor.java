package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jats.utfpl.instruction.AtomValue;
import jats.utfpl.instruction.CondIns;
import jats.utfpl.instruction.FuncCallIns;
import jats.utfpl.instruction.FuncDefIns;
import jats.utfpl.instruction.MoveIns;
import jats.utfpl.instruction.UtfplInstruction;
import jats.utfpl.instruction.ValPrim;
import jats.utfpl.tree.TID;

public class InstructionProcessor {
    
    static public CTemp ValPrim2CTemp(ValPrim vp, Map<TID, CTempID> map) {
        if (vp instanceof AtomValue) {
            return new CTempVal((AtomValue) vp);
        } else if (vp instanceof TID) {
            TID tid = (TID)vp;
            return TID2CTempID(tid, map);
        } else {
            throw new Error("shall not happen");
        }
    }
    
    static public CTempID TID2CTempID(TID tid, Map<TID, CTempID> map) {
        CTempID ret = map.get(tid);
        if (null == ret) {
            ret = new CTempID(tid);
            map.put(tid, ret);
        } 
        return ret;
    }
    
    static public List<CTemp> ValPrim2CTemp(List<ValPrim> vps, Map<TID, CTempID> map) {
        List<CTemp> ret = new ArrayList<CTemp>();
        for (ValPrim vp: vps) {
            ret.add(ValPrim2CTemp(vp, map));            
        }
        return ret;
    }
    
    static public List<FuncDefIns> getAllFunctions(List<UtfplInstruction> inslst) {
        // Assume here that there is no function inside function for simplicity.
        List<FuncDefIns> funlst = new ArrayList<FuncDefIns>();
        for (UtfplInstruction ins: inslst) {
            if (ins instanceof FuncDefIns) {
                funlst.add((FuncDefIns) ins);
            }
        }
        return funlst;
    }
    
    static public Map<FuncDefIns, Boolean> markSideEffectFunLst(List<FuncDefIns> funlst) {
        // todo
        Map<FuncDefIns, Boolean> fmap = new HashMap<FuncDefIns, Boolean>();
        for (FuncDefIns funDec: funlst) {
            // assume that all user-defined functions have side effect.
            fmap.put(funDec, true);
            funDec.flagSideEffect();
        }
        
        return fmap;
        
    }
    
    // Keep the input list unmodified.
    static public List<UtfplInstruction> addInsForGlobalVar(List<UtfplInstruction> inslst) {
        // todo
        return null;
        
    }
    
    // translate UtfplInstruction's into a preliminary form of CSPS instructions
    // Turning ValPrim into CTemp
    static List<CGroup> InsLst2CGroupLst(List<UtfplInstruction> inslst) {
        Map<TID, CTempID> map = new HashMap<TID, CTempID>();
        
        List<CGroup> insGroupList = new ArrayList<CGroup>();
        CEventBlock cblock = new CEventBlock();
        
        for (UtfplInstruction ins: inslst) {
            if (ins instanceof MoveIns) {
                MoveIns aIns = (MoveIns)ins;
                CIMove nIns = new CIMove(TID2CTempID(aIns.m_holder, map), ValPrim2CTemp(aIns.m_vp, map));
                cblock.add(nIns);
                if (ins.hasSideEffect()) {
                    insGroupList.add(cblock);
                    cblock = new CEventBlock();
                } else {
                    continue;
                }
            } else if (ins instanceof FuncCallIns) {
                FuncCallIns aIns = (FuncCallIns)ins;
                if (ins.hasSideEffect()) {
                    CProcessCallBlock cprocess = new CProcessCallBlock(
                            TID2CTempID(aIns.m_funlab, map), 
                            ValPrim2CTemp(aIns.m_args, map), 
                            TID2CTempID(aIns.m_holder, map));
                    if (0 != cblock.size()) {
                        insGroupList.add(cblock);
                        cblock = new CEventBlock();
                    }
                    insGroupList.add(cprocess);
                } else {
                    CIFunCall nIns = new CIFunCall(TID2CTempID(aIns.m_funlab, map), ValPrim2CTemp(aIns.m_args, map), TID2CTempID(aIns.m_holder, map));
                    cblock.add(nIns);
                }
            } else if (ins instanceof CondIns) {
                CondIns aIns = (CondIns)ins;
                if (ins.hasSideEffect()) {
                    List<CGroup> btrue = InsLst2CGroupLst(aIns.m_btrue);
                    List<CGroup> bfalse = InsLst2CGroupLst(aIns.m_bfalse);
                    
                    CCondBlock ccond = new CCondBlock(ValPrim2CTemp(aIns.m_cond, map), btrue, bfalse, TID2CTempID(aIns.m_holder, map));
                    if (0 != cblock.size()) {
                        insGroupList.add(cblock);
                        cblock = new CEventBlock();
                    }
                    insGroupList.add(ccond);
                    
                } else {
                    throw new Error("todo");
                }
            } else {
                // simply neglect
            }
        }
        return insGroupList;
    }
    
    // assign location to CTempID
    static public List<CProcess> CGroupLst2CProcess(TID name, List<CTempID> paras, List<CGroup> cgs) {
        StackLocation loc = new StackLocation();
        
        Iterator<CGroup> iter = cgs.iterator();
        while (iter.hasNext()) {
            CGroup cg = iter.next();
            if (cg instanceof CEventBlock) {
                CEventBlock cblock = (CEventBlock)cg;
                AnalyzeCBlock(cblock, loc);
            } else if (cg instanceof CCondBlock) {
                CCondBlock cb = (CCondBlock)cg;
                StackLocation locT = loc;
                StackLocation locF = loc.clone();
                // todo
                // parse true branch
                // parse false branch
                
                // has to do a mapping
                // making a value reference into a parameter reference.
                // todo
                // create a new process
                // add a new CProcessCallBlock
                
            }
        }
    }
    
    static public void AnalyzeCBlock(CEventBlock block, StackLocation loc) {
        // todo
    
    }


}
