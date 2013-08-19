package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
    
    static public CTemp ValPrim2CTemp(ValPrim vp, Map<TID, CTempID> map, TID funLab, CGroup grp) {
        if (vp instanceof AtomValue) {
            return new CTempVal((AtomValue) vp);
        } else if (vp instanceof TID) {
            TID tid = (TID)vp;
            return TID2CTempID(tid, map, funLab, grp);
        } else {
            throw new Error("shall not happen");
        }
    }
    
    static public CTempID TID2CTempID(TID tid, Map<TID, CTempID> map, TID funLab, CGroup grp) {
        CTempID ret = map.get(tid);
        if (null == ret) {
            ret = CTempID.createDef(tid, funLab, grp);
            map.put(tid, ret);  // This is important.
        } else {
            ret.addUsageLoc(funLab, grp);
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
    
    /*
     * Check whether this function has side effect.
     */
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
    
    /*
     * Keep the input list unmodified. One instruction can deal
     * with only one global variable.
     */
    static public List<UtfplInstruction> addInsForGlobalVar(List<UtfplInstruction> inslst) {
        // todo
        return null;
        
    }
    
    /*
     * translate UtfplInstruction's into a preliminary form of CSPS instructions
     * Turning ValPrim into CTemp, or in essence turning TID into CTempID. 
     * The identity of each CTempID is important. It's possible that two different
     * CTempID has the same TID.
     * subMap: inout
     */
    
    static List<CProcess> InsLst2CGroupLst(List<UtfplInstruction> inslst, 
                                         Map<TID, CTempID> subMap,
                                         TID funLab,
                                         List<TID> paraLst
                                         ) {
        List<CTempID> cParaLst = new ArrayList<CTempID>();
        for (TID para: paraLst) {
            CTempID cPara = TID2CTempID(para, subMap, funLab, null);
            cParaLst.add(cPara);
        }

        List<CGroup> insGroupList = new ArrayList<CGroup>();
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
                    CProcessCallBlock cprocess = new CProcessCallBlock(
                            aIns.m_funlab, 
                            ValPrimLst2CTempLst(aIns.m_args, subMap), 
                            TID2CTempID(aIns.m_holder, subMap));
                    if (0 != cblock.size()) {
                        insGroupList.add(cblock);
                        cblock = new CEventBlock();
                    }
                    insGroupList.add(cprocess);
                } else {
                    CIFunCall nIns = new CIFunCall(aIns.m_funlab, 
                    		                       ValPrimLst2CTempLst(aIns.m_args, subMap), 
                    		                       TID2CTempID(aIns.m_holder, subMap));
                    cblock.add(nIns);
                }
            } else if (ins instanceof CondIns) {
                CondIns aIns = (CondIns)ins;
                if (ins.hasSideEffect()) {
                	CTempID ctTrue = new CTempID(aIns.m_holder);
                	CTempID ctFalse = new CTempID(aIns.m_holder);
                	CTempID ctHolder = new CTempID(aIns.m_holder);
                	
                	Map<TID, CTempID> subMapTrue = new HashMap<TID, CTempID>(subMap);
                	Map<TID, CTempID> subMapFalse = new HashMap<TID, CTempID>(subMap);
                	
                	subMapTrue.put(aIns.m_holder, ctTrue);
                	subMapFalse.put(aIns.m_holder, ctFalse);
                	subMap.put(aIns.m_holder, ctHolder);
                	
                    List<CGroup> btrue = InsLst2CGroupLst(aIns.m_btrue, subMapTrue);
                    List<CGroup> bfalse = InsLst2CGroupLst(aIns.m_bfalse, subMapFalse);
                    
                    CCondBlock ccond = new CCondBlock(
                    		ValPrim2CTemp(aIns.m_cond, subMap), 
                    		btrue, 
                    		bfalse, 
                    		ctHolder,
                    		ctTrue,
                    		ctFalse);
                    // Now there are 3 CTempID for one TID.
                    
                    if (0 != cblock.size()) {
                        insGroupList.add(cblock);
                        cblock = new CEventBlock();
                    }
                    insGroupList.add(ccond);
                    
                } else {
                    throw new Error("todo");
                }
            } else if (ins instanceof FuncDefIns) {
                FuncDefIns fun = FunDef2CGroupLst((FuncDefIns)ins, subMap);
                if (0 != cblock.size()) {
                    insGroupList.add(cblock);
                    cblock = new CEventBlock();
                }
                insGroupList.add(fun);
                
            } else {
            	throw new Error("no such case");
            	
            }
        }
        return insGroupList;
    }
    
    static FuncDefIns FunDef2CGroupLst(FuncDefIns funDef, Map<TID, CTempID> subMap) {
    	
    	// todo
    	return null;
    	
    }
    
    // change "from" to "to"
    static public List<CGroup> CGroupLstSubs(List<CGroup> cgs, Map<CTempID, CTempID> subMap) {
        // todo
        return null;
    }
    
    // assign location to CTempID
    // cgs: input
    // extraProcess: output: Extra process generated will be appended to the list
    // Caution: The inut "cgs" is changed when the function returns.
    static public List<CGroup> CGroupLst2CProcess(
            TID funLab, CTempID retHolder, List<CTempID> paras, 
            List<CGroup> cgs, StackLocation loc, 
            List<CProcess> extraProcesses) {
        
        List<CGroup> retList = new ArrayList<CGroup>();
        
        ListIterator<CGroup> iter = cgs.listIterator();
        while (iter.hasNext()) {
            CGroup cg = iter.next();
            if (cg instanceof CEventBlock) {
                CEventBlock cblock = (CEventBlock)cg;
                AnalyzeCBlock(cblock, loc);
                retList.add(cblock);
            } else if (cg instanceof CCondBlock) {
                CCondBlock cb = (CCondBlock)cg;
                
                // parse the true branch
                StackLocation locT = loc.clone();
                CGroupLst2CProcess(funLab, cb.getHolder(), paras, 
                        cb.getTrueBranch(), locT, extraProcesses)
                cb.setTrueBranch(
                        );
                
                StackLocation locF = loc.clone();
                CGroupLst2CProcess(funLab, cb.getHolder(), paras, 
                        cb.getFalseBranch(), locF, extraProcesses)
                cb.setFalseBranch(
                        );
                
                retList.add(cb);
                
                if (iter.hasNext()) {
                    // extra para for the newly created process
                    TID newParaTID = TID.createPara(cb.getHolder().getID());
                    CTempID newPara = new CTempID(newParaTID);

                    Map<CTempID, CTempID> subMap = new HashMap<CTempID, CTempID>();
                    subMap.put(cb.getHolder(), newPara);
                    
                    TID newRetTID = TID.createLocalVar("ret");
                    CTempID newRetHolder = new CTempID(newRetTID);
                    subMap.put(retHolder, newRetHolder);
                    
                    // substitute new CTempID
                    int nextInd = iter.nextIndex();
                    List<CGroup> restCgs = CGroupLstSubs(cgs.subList(nextInd, cgs.size()), subMap);
                    
                    
                    // name for the newly created process
                    TID newFunLab = TID.createUserFun(funLab.getID() + "_if_");

                    // reuse the paras, no substitution
                    List<CTempID> newParas = new ArrayList<CTempID>(paras);
                    newParas.add(newPara);

                    List<CGroup> newCgs = CGroupLst2CProcess(newFunLab, newRet, newParas, restCgs, loc, extraProcesses);
                    CProcess newProcess = new CProcess(newFunLab, newParas, newCgs);
                    extraProcesses.add(newProcess);
                    
                    CProcessCallBlock newGroup= new CProcessCallBlock(newFunLab, xx, cb.getHolder());
                    retList.add(newGroup);
                    return retList;
                    
                } else {
                    
                    
                }

                break;
                
                
                
                
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
        
        // todo
        return null;
    }
    
    static public void AnalyzeCBlock(CEventBlock block, StackLocation loc) {
        // todo
    
    }


}
