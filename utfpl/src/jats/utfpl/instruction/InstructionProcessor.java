package jats.utfpl.instruction;

import jats.utfpl.tree.TID;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class InstructionProcessor {
	
    static public ValPrim subsVP(ValPrim vp, Map<TID, TID> subMap) {
        if (vp instanceof AtomValue) {
            return vp;
        } else if (vp instanceof TID) {
            return subsTID((TID)vp, subMap);
        } else {
            throw new Error("not supported");
        }
    }

    static public TID subsTID(TID tid, Map<TID, TID> subMap) {
        TID newTID = subMap.get(tid);
        if (null == newTID) {
            return tid;
        } else {
            return newTID;
        }
    }
    
    static public List<ValPrim> subsVPLst(List<ValPrim> vpLst, Map<TID, TID> subMap) {
        List<ValPrim> newVpLst = new ArrayList<ValPrim>();
        for (ValPrim vp: vpLst) {
            ValPrim newVp = subsVP(vp, subMap);
            newVpLst.add(newVp);
        }
        return newVpLst;
    }
    
	/*
	 * Create a new list and the old list is unchanged.
	 * Substitution is done along the way as well. Another way to put it,
	 * the true instruction list is based on both "insLst" and "subMap".
	 */
	static public List<UtfplInstruction> InsLstProcess(
			List<UtfplInstruction> insLst, 
			Map<TID, TID> subMap,
			TID FuncLab,
			TID retHolder) {
		
		List<UtfplInstruction> nList = new ArrayList<UtfplInstruction>();
		
		ListIterator<UtfplInstruction> iter = insLst.listIterator();
		while (iter.hasNext()) {
			UtfplInstruction ins = iter.next();
			if (ins instanceof MoveIns) {
                MoveIns aIns = (MoveIns)ins;
                MoveIns nIns = aIns.createSubs(subMap);
                nList.add(nIns);   
			} else if (ins instanceof CondIns) {
				CondIns aIns = (CondIns)ins;
				
				TID condRetHolder = subsTID(aIns.m_holder, subMap);
				ValPrim aCond = subsVP(aIns.m_cond, subMap);
				
				List<UtfplInstruction> insLstTrue = InsLstProcess(aIns.m_btrue, subMap, FuncLab, retHolder);
				List<UtfplInstruction> insLstFalse = InsLstProcess(aIns.m_bfalse, subMap, FuncLab, retHolder);
				if (iter.hasNext()) {  // has more instructions to deal with
					if (!aIns.m_holder.isGlobal()) {  // Global variable as the holder, no need to merge.
                        // Since aIns is not the last instruction, aRetHolder !=
                        // retHolder.

                        // The instructions afterwards.
                        int nextInd = iter.nextIndex();
                        List<UtfplInstruction> restLst = insLst.subList(
                                nextInd, insLst.size());

                        // The new function is actually a closure with only one
                        // parameter.
                        List<TID> newParas = new ArrayList<TID>();

                        TID extraPara = TID.createPara(condRetHolder.getID());
                        newParas.add(extraPara);
                        // replace the new parameter into the following
                        // instructions
                        subMap.put(condRetHolder, extraPara);

                        TID newFuncLab = TID.createUserFun(FuncLab.getID()
                                + "_if");

                        TID newRetHolder = null;
                        if (null != retHolder && TID.ANONY != retHolder) {
                            newRetHolder = TID.createRetHolder(FuncLab.getID()
                                    + "_ret");
                        } else {
                            newRetHolder = TID.ANONY;
                        }
                        subMap.put(retHolder, newRetHolder);

                        List<UtfplInstruction> newFuncBody = InsLstProcess(
                                restLst, subMap, newFuncLab, newRetHolder);
                        FuncDefIns newFuncDef = new FuncDefIns(newFuncLab,
                                newParas, newFuncBody, newRetHolder);

                        List<ValPrim> newArgs = new ArrayList<ValPrim>();
                        newArgs.add(condRetHolder);
                        FuncCallIns newFuncCall = new FuncCallIns(retHolder,
                                newFuncLab, newArgs);
                        nList.add(newFuncDef); // maybe a closure

                        insLstTrue.add(newFuncCall);
                        insLstFalse.add(newFuncCall);
					}
				}
                CondIns nIns = new CondIns(retHolder, aCond, insLstTrue, insLstFalse);
                nList.add(nIns);
                break;  // done, no more instructions
			} else if (ins instanceof FuncCallIns) {
			    FuncCallIns aIns = (FuncCallIns)ins;
			    FuncCallIns nIns = aIns.createSubs(subMap);
			    nList.add(nIns);
				
			} else if (ins instanceof FuncDefIns) {
			    FuncDefIns aIns = (FuncDefIns)ins;
			    List<UtfplInstruction> newBody = InsLstProcess(aIns.m_body, subMap, aIns.m_name, aIns.m_ret);
			    FuncDefIns nIns = new FuncDefIns(aIns.m_name, aIns.m_paralst, newBody, aIns.m_ret);
			    nList.add(nIns);				
			} else {
			    throw new Error("Not supported");
			}
		}
		return nList;
		
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
     * Keep the input list unmodified. One instruction can deal
     * with only one global variable.
     */
    static public List<UtfplInstruction> addInsForGlobalVar(List<UtfplInstruction> inslst) {
        // todo
        return null;
        
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
}


