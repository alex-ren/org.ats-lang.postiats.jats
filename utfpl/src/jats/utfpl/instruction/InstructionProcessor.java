package jats.utfpl.instruction;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class InstructionProcessor {
    
    /*
     * The content of process includes the following.
     * 1. One block contains only one operation related to global variable.
     * 2. Transformation of "if" branch.
     */
    public ProgramIns process(ProgramIns inputProg) {
        
        Map<TID, TID> subMap = new HashMap<TID, TID>();
        TID mainFunLab = TID.createUserFun("main");
        
        // todo
        // call addInsForGlobalVar
        
        List<UtfplInstruction> insLst = InsLstProcess(inputProg.getInsLst(), subMap, mainFunLab, TID.ANONY);
        InsLstProcessRetCall(insLst, mainFunLab);
        return new ProgramIns(inputProg.getGlobalVars(), insLst);
    }
	
    static public ValPrim subsVP(ValPrim vp, Map<TID, TID> subMap) {
        if (vp instanceof AtomValue) {
            return vp;
        } else if (vp instanceof TID) {
            return TID.subsTID((TID)vp, subMap);
        } else {
            throw new Error("not supported");
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
    /*
     * Effect analysis is done after this stage.
     */
	static private List<UtfplInstruction> InsLstProcess(
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
				
				TID condRetHolder = TID.subsTID(aIns.m_holder, subMap);
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
	
    /*
     * Effect analysis should be done before this stage.
     */
    static private void InsLstProcessRetCall(
            List<UtfplInstruction> insLst, TID FuncLab) {
        ListIterator<UtfplInstruction> iter = insLst.listIterator();
        while (iter.hasNext()) {
            UtfplInstruction ins = iter.next();
            if (ins instanceof MoveIns) {
                // no-op
            } else if (ins instanceof CondIns) {
                CondIns aIns = (CondIns)ins;
                InsLstProcessRetCall(aIns.m_btrue, FuncLab);
                InsLstProcessRetCall(aIns.m_bfalse, FuncLab);
            } else if (ins instanceof FuncCallIns) {
                FuncCallIns aIns = (FuncCallIns) ins;
                if (aIns.hasSideEffect()) {
                    if (aIns.isRet()) {
                        // Add one more move instruction for the case that last
                        // instruction
                        // is a call of function which has side-effect. Such
                        // function will
                        // be turned into process in the next stage.
                        TID tempHolder = TID.createLocalVar(FuncLab.getID()
                                + "_tret");
                        TID endHolder = aIns.m_holder;
                        aIns.m_holder = tempHolder;
                        MoveIns extraIns = new MoveIns(endHolder, tempHolder);
                        iter.add(extraIns);
                    }
                }
            } else if (ins instanceof FuncDefIns) {
                FuncDefIns aIns = (FuncDefIns)ins;
                InsLstProcessRetCall(aIns.m_body, aIns.m_name);  
            } else {
                throw new Error("Not supported");
            }
        }
        return;
    }
	
	
    static private List<FuncDefIns> getAllFunctions(List<UtfplInstruction> inslst) {
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
    static private List<UtfplInstruction> addInsForGlobalVar(List<UtfplInstruction> inslst) {
        // todo
        return inslst;
        
    }
    

    
    /*
     * Check whether this function has side effect.
     */
    static private Map<FuncDefIns, Boolean> markSideEffectFunLst(List<FuncDefIns> funlst) {
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



