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
     * 1. Group instructions into block. Prepare for the analysis of each values (def / use).
     * 2. Decide whether a value is used out of its visible scope.
     * 3. Add the concept of stack location.
     */
    public ProgramCSPS trans(ProgramIns inputProg) {
        // 
        List<CIProcessDef> procLst = new ArrayList<CIProcessDef>();
        Map<TID, VariableInfo> subMap = new HashMap<TID, VariableInfo>();
        TID mainLab = TID.createUserFun("main");
        List<CBlock> body = InsLst2CBlockLst(inputProg.getInsLst(), subMap, mainLab, procLst, 0);

        // add the concept of stack
        processBlockLstForStack(body);
        
        // remove the CIProcessDef
        CSPSEraser eraser= new CSPSEraser(body);
        eraser.erase();

        ProgramCSPS outputProg = new ProgramCSPS(inputProg.getGlobalVars(), body, procLst);
        return outputProg;
    }
    
    /*
     * The processing includes the following.
     * 1. Decide whether a value is used out of its visible scope.
     * 2. Add the concept of stack location according to the analysis result of 1.
     *    After this, there would be no identical CTempID at all.
     */
    static private void processBlockLstForStack(List<CBlock> blkLst) {
        int offset = 0;
        for (CBlock blk: blkLst) {
            offset = blk.process(offset);            
        }
    }
    
    /*
     * Get an object of CTemp from an object of ValPrim.
     * "map" may be updated.
     * "grp" is the group which holds the object of CTemp.
     */
    static private CTemp ValPrim2CTemp(ValPrim vp, Map<TID, VariableInfo> map, TID funLab, CBlock grp, int level) {
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
    static private List<CTemp> ValPrimLst2CTempLst(List<ValPrim> vpLst, Map<TID, VariableInfo> map, TID funLab, CBlock grp, int level) {
    	List<CTemp> nLst = new ArrayList<CTemp>();
    	for (ValPrim vp: vpLst) {
    		CTemp newVp = ValPrim2CTemp(vp, map, funLab, grp, level);
    		nLst.add(newVp);
    	}
    	return nLst;
    }
    
    /*
     * If "VariableInfo" is not in the map, then it's a definition. 
     * Otherwise, it's a usage.
     */
    static private CTempID TID2CTempID(TID tid, Map<TID, VariableInfo> map, TID funLab, CBlock grp, int level) {
        EntityLocation loc = EntityLocation.create(tid, grp, level);
        CTempID ret = null;
        
        VariableInfo vi = map.get(tid);
        if (null == vi) {
            vi = VariableInfo.create(tid, loc);
            map.put(tid, vi);  // This is important.
            ret = CTempID.createAsDef(vi, loc);
        } else {
            vi.addUsage(loc);
            ret = CTempID.createAsUsage(vi, loc);
        }
        return ret;
    }
    
    /*
     * translate UtfplInstruction's into a preliminary form of CSPS instructions
     * Turning ValPrim into CTemp, or in essence turning TID into CTempID. 
     * The identity of each CTempID is important. It's possible that two different
     * CTempID has the same TID in the case for CCondBlock.
     * subMap: inout
     * outProcs: output
     *   The new functions defined in the input "inslst" will be put into the list.
     */
    static private List<CBlock> InsLst2CBlockLst(List<UtfplInstruction> inslst, 
                                         Map<TID, VariableInfo> subMap,
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
                	
                	Map<TID, VariableInfo> subMapTrue = new HashMap<TID, VariableInfo>(subMap);
                	Map<TID, VariableInfo> subMapFalse = new HashMap<TID, VariableInfo>(subMap);
                	
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
                    Map<TID, VariableInfo> innerSubMap = new HashMap<TID, VariableInfo>(subMap);
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
            , Map<TID, VariableInfo> subMap
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




