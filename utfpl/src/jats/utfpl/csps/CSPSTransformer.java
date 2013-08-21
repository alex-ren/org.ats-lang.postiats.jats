package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jats.utfpl.instruction.AtomValue;
import jats.utfpl.instruction.CondIns;
import jats.utfpl.instruction.FuncCallIns;
import jats.utfpl.instruction.FuncDefIns;
import jats.utfpl.instruction.MoveIns;
import jats.utfpl.instruction.ProgramIns;
import jats.utfpl.instruction.TID;
import jats.utfpl.instruction.UtfplInstruction;
import jats.utfpl.instruction.ValPrim;

public class CSPSTransformer {
    
    /*
     * The transformation includes the following.
     * 1. Group instructions into block.
     * 2. Decide whether a value is used out of its visible scope.
     * 3. Add the concept of stack location.
     */
    public ProgramCSPS trans(ProgramIns inputProg) {
        List<CIProcessDef> procLst = new ArrayList<CIProcessDef>();
        Map<TID, CTempID> subMap = new HashMap<TID, CTempID>();
        TID mainLab = TID.createUserFun("main");
        List<CBlock> body = InsLst2CBlockLst(inputProg.getInsLst(), subMap, mainLab, procLst, 0);

        // todo
        // add stack concept
        
        ProgramCSPS outputProg = new ProgramCSPS(inputProg.getGlobalVars(), body, procLst);
        return outputProg;
    }
    
    static private void processBlockLst(List<CBlock> blkLst, int level) {
        int offset = 0;
        for (CBlock blk: blkLst) {
            blk.process(level);            
        }
    }
    
    /*
     * Get an object of CTemp from an object of ValPrim.
     * "map" may be updated.
     * "grp" is the group which holds the object of CTemp.
     */
    static private CTemp ValPrim2CTemp(ValPrim vp, Map<TID, CTempID> map, TID funLab, CBlock grp, int level) {
        if (vp instanceof AtomValue) {
            return new CTempVal((AtomValue) vp);
        } else if (vp instanceof TID) {
            TID tid = (TID)vp;
            return TID2CTempID(tid, map, funLab, grp, level);
        } else {
            throw new Error("shall not happen");
        }
    }
    
    /*
     * Create a new list of CTemp from a list of ValPrim.
     */
    static private List<CTemp> ValPrimLst2CTempLst(List<ValPrim> vpLst, Map<TID, CTempID> map, TID funLab, CBlock grp, int level) {
    	List<CTemp> nLst = new ArrayList<CTemp>();
    	for (ValPrim vp: vpLst) {
    		CTemp newVp = ValPrim2CTemp(vp, map, funLab, grp, level);
    		nLst.add(newVp);
    	}
    	return nLst;
    }
    
    /*
     * If "tid" is not in the map, then it's a definition. 
     * Create a new CTempID and put it into the map.
     * Otherwise, "tid" is a usage, then update its field of usage.
     */
    static private CTempID TID2CTempID(TID tid, Map<TID, CTempID> map, TID funLab, CBlock grp, int level) {
        CTempID ret = map.get(tid);
        if (null == ret) {
            ret = CTempID.createDef(tid, funLab, grp, level);
            map.put(tid, ret);  // This is important.
        } else {
            ret.addUsageLoc(funLab, grp, level);
        }
        return ret;
    }
    
    /*
     * translate UtfplInstruction's into a preliminary form of CSPS instructions
     * Turning ValPrim into CTemp, or in essence turning TID into CTempID. 
     * The identity of each CTempID is important. It's possible that two different
     * CTempID has the same TID.
     * subMap: inout
     * outProcs: output
     *   The new functions defined in the input "inslst" will be put into the list.
     */
    static private List<CBlock> InsLst2CBlockLst(List<UtfplInstruction> inslst, 
                                         Map<TID, CTempID> subMap,
                                         TID funLab,
                                         List<CIProcessDef> outProcs,
                                         int level
                                         ) {

        List<CBlock> insBlockList = new ArrayList<CBlock>();
        CEventBlock cblock = new CEventBlock(level);
        
        for (UtfplInstruction ins: inslst) {
            if (ins instanceof MoveIns) {
                MoveIns aIns = (MoveIns)ins;
                CTempID ctHolder = TID2CTempID(aIns.m_holder, subMap, funLab, cblock, level);
                CTemp ctValue = ValPrim2CTemp(aIns.m_vp, subMap, funLab, cblock, level);
                CIMove nIns = new CIMove(ctHolder, ctValue, cblock);
                cblock.add(nIns);
                if (aIns.hasSideEffect()) {
                    insBlockList.add(cblock);
                    cblock = new CEventBlock(level);
                } else {
                    continue;
                }
            } else if (ins instanceof FuncCallIns) {
                FuncCallIns aIns = (FuncCallIns)ins;
                if (aIns.hasSideEffect()) {
                    CProcessCallBlock cprocess = new CProcessCallBlock(aIns.m_funlab, level);

                	List<CTemp> nLst = ValPrimLst2CTempLst(aIns.m_args, subMap, funLab, cprocess, level);
                	CTempID ctHolder = TID2CTempID(aIns.m_holder, subMap, funLab, cprocess, level);
                    cprocess.reset(nLst, ctHolder);
                    
                    if (0 != cblock.size()) {
                        insBlockList.add(cblock);
                        cblock = new CEventBlock(level);
                    }
                    insBlockList.add(cprocess);
                } else {
                	List<CTemp> nLst = ValPrimLst2CTempLst(aIns.m_args, subMap, funLab, cblock, level);
                	CTempID ctHolder = TID2CTempID(aIns.m_holder, subMap, funLab, cblock, level);
                    CIFunCall nIns = new CIFunCall(aIns.m_funlab, nLst, ctHolder, cblock);

                    cblock.add(nIns);
                }
            } else if (ins instanceof CondIns) {
                CondIns aIns = (CondIns)ins;
                if (ins.hasSideEffect()) {
                    CCondBlock ccond = new CCondBlock(level);
                    CTemp ctCond = ValPrim2CTemp(aIns.m_cond, subMap, funLab, ccond, level);
                	
                	Map<TID, CTempID> subMapTrue = new HashMap<TID, CTempID>(subMap);
                	Map<TID, CTempID> subMapFalse = new HashMap<TID, CTempID>(subMap);
                	
                    List<CBlock> btrue = InsLst2CBlockLst(aIns.m_btrue, subMapTrue, funLab, outProcs, level);
                    List<CBlock> bfalse = InsLst2CBlockLst(aIns.m_bfalse, subMapFalse, funLab, outProcs, level);
                    
                    // connect all the links
                    ccond.reset(ctCond, btrue, bfalse);
                    
                    if (0 != cblock.size()) {
                        insBlockList.add(cblock);
                        cblock = new CEventBlock(level);
                    }
                    insBlockList.add(ccond);
                    
                } else {
                    throw new Error("todo");
                }
            } else if (ins instanceof FuncDefIns) {
                FuncDefIns aIns = (FuncDefIns)ins;
                if (ins.hasSideEffect()) {
                    Map<TID, CTempID> innerSubMap = new HashMap<TID, CTempID>(subMap);
                    List<CIProcessDef> innerProcs = new ArrayList<CIProcessDef>();
                    CIProcessDef cProc = FunDef2CProcess(aIns, innerSubMap, innerProcs, cblock, level + 1);
                    cblock.add(cProc);
                    
                    outProcs.add(cProc);
                    outProcs.addAll(innerProcs);
                } else {
                    throw new Error("todo");
                }

                
            } else {
            	throw new Error("no such case");
            	
            }
        }
        
        if (0 != cblock.size()) {
            insBlockList.add(cblock);
        }
        return insBlockList;
    }
    
    static private CIProcessDef FunDef2CProcess(
            FuncDefIns funDef
            , Map<TID, CTempID> subMap
            , List<CIProcessDef> outProcs
            , CBlock blk
            , int level) {
        List<CTempID> paras = new ArrayList<CTempID>();
        for (TID para: funDef.m_paralst) {
            CTempID cPara = TID2CTempID(para, subMap, funDef.m_name, null, level);
            paras.add(cPara);
        }
        
        List<CBlock> body = InsLst2CBlockLst(funDef.m_body, subMap, funDef.m_name, outProcs, level);
        CIProcessDef cProc = new CIProcessDef(funDef.m_name, paras, body, blk);
        return cProc;
    }
}




