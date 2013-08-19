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
import jats.utfpl.instruction.UtfplInstruction;
import jats.utfpl.instruction.ValPrim;
import jats.utfpl.tree.TID;

public class CSPSTransformer {
    
    /*
     * Get an object of CTemp from an object of ValPrim.
     * "map" may be updated.
     * "grp" is the group which holds the object of CTemp.
     */
    static public CTemp ValPrim2CTemp(ValPrim vp, Map<TID, CTempID> map, TID funLab, CBlock grp) {
        if (vp instanceof AtomValue) {
            return new CTempVal((AtomValue) vp);
        } else if (vp instanceof TID) {
            TID tid = (TID)vp;
            return TID2CTempID(tid, map, funLab, grp);
        } else {
            throw new Error("shall not happen");
        }
    }
    
    /*
     * Create a new list of CTemp from a list of ValPrim.
     */
    static public List<CTemp> ValPrimLst2CTempLst(List<ValPrim> vpLst, Map<TID, CTempID> map, TID funLab, CBlock grp) {
    	List<CTemp> nLst = new ArrayList<CTemp>();
    	for (ValPrim vp: vpLst) {
    		CTemp newVp = ValPrim2CTemp(vp, map, funLab, grp);
    		nLst.add(newVp);
    	}
    	return nLst;
    }
    
    /*
     * If "tid" is not in the map, then it's a definition. 
     * Create a new CTempID and put it into the map.
     * Otherwise, "tid" is a usage, then update its field of usage.
     */
    static public CTempID TID2CTempID(TID tid, Map<TID, CTempID> map, TID funLab, CBlock grp) {
        CTempID ret = map.get(tid);
        if (null == ret) {
            ret = CTempID.createDef(tid, funLab, grp);
            map.put(tid, ret);  // This is important.
        } else {
            ret.addUsageLoc(funLab, grp);
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
    public static List<CBlock> InsLst2CGroupLst(List<UtfplInstruction> inslst, 
                                         Map<TID, CTempID> subMap,
                                         TID funLab,
                                         List<CProcess> outProcs
                                         ) {

        List<CBlock> insGroupList = new ArrayList<CBlock>();
        CEventBlock cblock = new CEventBlock();
        
        for (UtfplInstruction ins: inslst) {
            if (ins instanceof MoveIns) {
                MoveIns aIns = (MoveIns)ins;
                CTempID ctHolder = TID2CTempID(aIns.m_holder, subMap, funLab, cblock);
                CTemp ctValue = ValPrim2CTemp(aIns.m_vp, subMap, funLab, cblock);
                CIMove nIns = new CIMove(ctHolder, ctValue);
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
                    CProcessCallBlock cprocess = new CProcessCallBlock(aIns.m_funlab);

                	List<CTemp> nLst = ValPrimLst2CTempLst(aIns.m_args, subMap, funLab, cprocess);
                	CTempID ctHolder = TID2CTempID(aIns.m_holder, subMap, funLab, cprocess);
                    cprocess.reset(nLst, ctHolder);
                    
                    if (0 != cblock.size()) {
                        insGroupList.add(cblock);
                        cblock = new CEventBlock();
                    }
                    insGroupList.add(cprocess);
                } else {
                	List<CTemp> nLst = ValPrimLst2CTempLst(aIns.m_args, subMap, funLab, cblock);
                	CTempID ctHolder = TID2CTempID(aIns.m_holder, subMap, funLab, cblock);
                    CIFunCall nIns = new CIFunCall(aIns.m_funlab, nLst, ctHolder);

                    cblock.add(nIns);
                }
            } else if (ins instanceof CondIns) {
                CondIns aIns = (CondIns)ins;
                if (ins.hasSideEffect()) {
                    CCondBlock ccond = new CCondBlock();
                    CTemp ctCond = ValPrim2CTemp(aIns.m_cond, subMap, funLab, ccond);
                	
                	Map<TID, CTempID> subMapTrue = new HashMap<TID, CTempID>(subMap);
                	Map<TID, CTempID> subMapFalse = new HashMap<TID, CTempID>(subMap);
                	
                    List<CBlock> btrue = InsLst2CGroupLst(aIns.m_btrue, subMapTrue, funLab, outProcs);
                    List<CBlock> bfalse = InsLst2CGroupLst(aIns.m_bfalse, subMapFalse, funLab, outProcs);
                    
                    // connect all the links
                    ccond.reset(ctCond, btrue, bfalse);
                    
                    if (0 != cblock.size()) {
                        insGroupList.add(cblock);
                        cblock = new CEventBlock();
                    }
                    insGroupList.add(ccond);
                    
                } else {
                    throw new Error("todo");
                }
            } else if (ins instanceof FuncDefIns) {
                FuncDefIns aIns = (FuncDefIns)ins;
                if (ins.hasSideEffect()) {
                    Map<TID, CTempID> innerSubMap = new HashMap<TID, CTempID>(subMap);
                    List<CProcess> innerProcs = new ArrayList<CProcess>();
                    CProcess cProc = FunDef2CProcess(aIns, innerSubMap, innerProcs);
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
            insGroupList.add(cblock);
        }
        return insGroupList;
    }
    
    public static CProcess FunDef2CProcess(
            FuncDefIns funDef
            , Map<TID, CTempID> subMap
            , List<CProcess> outProcs) {
        List<CTempID> paras = new ArrayList<CTempID>();
        for (TID para: funDef.m_paralst) {
            CTempID cPara = TID2CTempID(para, subMap, funDef.m_name, null);
            paras.add(cPara);
        }
        
        List<CBlock> body = InsLst2CGroupLst(funDef.m_body, subMap, funDef.m_name, outProcs);
        CProcess cProc = new CProcess(funDef.m_name, paras, body);
        return cProc;
    }
}




