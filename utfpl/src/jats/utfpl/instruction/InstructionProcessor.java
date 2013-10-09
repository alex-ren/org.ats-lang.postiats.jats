package jats.utfpl.instruction;

import jats.utfpl.patcsps.type.PATTypeSingleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;


public class InstructionProcessor {
    
    /*
     * The content of process includes the following.
     * 1. One instruction contains only one operator related to global variable.
     * 2. Transformation of "if" branch.
     */
    public ProgramIns process(ProgramIns inputProg) {
        
        Map<TID, TID> subMap = new HashMap<TID, TID>();
        TID mainFunLab = TID.createUserFun("main");
        
        GlobalVarInsProcessor visitor = new GlobalVarInsProcessor();
        List<UtfplInstruction> insLst1 = visitor.addInsForGlobalVar(inputProg.getInsLst());
        
        // Here we need to add one more instruction to main's body, which is
        // main_ret = ().
        // Test case
        // val y = if 1 then 2 else 3
        // val z = 3
        // Basically, treat main body as a function without parameters.
        TID mainRet = TID.createRetHolder("main_ret");
        UtfplInstruction mainEnd = new MoveIns(mainRet, TupleValue.cNone);
        insLst1.add(mainEnd);
        
        List<FuncDefIns> allFuncs = new ArrayList<FuncDefIns>();
        List<UtfplInstruction> insLst2 = InsLstProcess(insLst1, subMap, mainFunLab, mainRet, allFuncs);

        markSideEffectFunLst(allFuncs);
        
        ClosureConverter cvt = new ClosureConverter();
        List<UtfplInstruction> insLst3 = cvt.convert(insLst2);
        
        return new ProgramIns(inputProg.getGlobalVars(), insLst3);
    }
	
    static public ValPrim subsVP(ValPrim vp, Map<TID, TID> subMap) {
        if (vp instanceof AtomValue) {
            return vp;
        } else if (vp instanceof TID) {
            return TID.subsTID((TID)vp, subMap);
        } else if (vp instanceof TupleValue) {
            if (vp != TupleValue.cNone) {
                throw new Error("Not supported");
            } else {
                return vp;
            }
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
	 * 
	 * The purpose of this function is to process if-branch -- turn the program
	 * into a "Static Single Assignment" form.
	 */
    /*
     * Effect analysis is done after this stage.
     */
	static private List<UtfplInstruction> InsLstProcess(
			List<UtfplInstruction> insLst, 
			Map<TID, TID> subMap,
			TID FuncLab,
			TID retHolder,
			List<FuncDefIns> allFuncs) {
		
		List<UtfplInstruction> nList = new ArrayList<UtfplInstruction>();
		
		ListIterator<UtfplInstruction> iter = insLst.listIterator();
		while (iter.hasNext()) {
			UtfplInstruction ins = iter.next();
			if (ins instanceof MoveIns) {
                MoveIns aIns = (MoveIns)ins;
                MoveIns nIns = aIns.createSubs(subMap);
                nList.add(nIns);
//                if (nIns.m_holder.isRet()) {
//                    nList.add(new ReturnIns(nIns.m_holder));
//                }
			} else if (ins instanceof CondIns) {
				CondIns aIns = (CondIns)ins;
				
				TID condRetHolder = TID.subsTID(aIns.m_holder, subMap);
//				System.out.println("cond holder is " + condRetHolder);
				ValPrim aCond = subsVP(aIns.m_cond, subMap);

                // copy the list
                List<UtfplInstruction> insLstTrue = aIns.m_btrue;
                List<UtfplInstruction> insLstFalse = aIns.m_bfalse;
                
                
				if (iter.hasNext() && // has next instruction, which is not "ReturnIns"
                        !aIns.m_holder.isGlobal()) { // Global variable as the holder, no need to merge.
                    // Since aIns is not the last instruction, aRetHolder != retHolder.

                    // The instructions afterwards.
                    int nextInd = iter.nextIndex();
                    List<UtfplInstruction> restLst = insLst.subList(nextInd,
                            insLst.size());

                    // The new function is actually a closure with only one parameter.
                    List<TID> newParas = new ArrayList<TID>();

                    TID aPara = TID.createPara(condRetHolder.getID(), false);
                    newParas.add(aPara);

                    HashMap<TID, TID> newSubMap = new HashMap<TID, TID>(subMap);
                    // replace the new parameter into the following
                    // instructions
                    newSubMap.put(condRetHolder, aPara); // <===== map 1

                    TID newFuncLab = TID.createUserFun(FuncLab.getID() + "_if");

                    TID newRetHolder = null;
                    if (null != retHolder) {
                        newRetHolder = TID.createRetHolder(FuncLab.getID()
                                + "_ret");
                    } else {
                        throw new Error("check this");
                    }
                    newSubMap.put(retHolder, newRetHolder); // <====== map 2

                    List<UtfplInstruction> newFuncBody = InsLstProcess(restLst,
                            newSubMap, newFuncLab, newRetHolder, allFuncs);

                    FuncDefIns newFuncDef = new FuncDefIns(newFuncLab,
                            newParas, newFuncBody, newRetHolder);
                    nList.add(newFuncDef); // maybe a closure
                    allFuncs.add(newFuncDef);

                    List<ValPrim> newArgs = new ArrayList<ValPrim>();
                    newArgs.add(condRetHolder);
                    FuncCallIns newFuncCall = new FuncCallIns(retHolder,
                            newFuncLab, newArgs);

                    // copy the list
                    insLstTrue = new ArrayList<UtfplInstruction>(aIns.m_btrue);
                    insLstFalse = new ArrayList<UtfplInstruction>(aIns.m_bfalse);

                    insLstTrue.add(newFuncCall);
                    insLstFalse.add(newFuncCall);
				}
				insLstTrue = InsLstProcess(insLstTrue, subMap, FuncLab, retHolder, allFuncs);
				insLstFalse = InsLstProcess(insLstFalse, subMap, FuncLab, retHolder, allFuncs);
                
                CondIns nIns = new CondIns(retHolder, aCond, insLstTrue, insLstFalse);
                nList.add(nIns);
                break;  // done, no more instructions
			} else if (ins instanceof FuncCallIns) {
			    FuncCallIns aIns = (FuncCallIns)ins;
			    FuncCallIns nIns = aIns.createSubs(subMap);
			    nList.add(nIns);
//                if (nIns.m_holder.isRet()) {
//                    nList.add(new ReturnIns(nIns.m_holder));
//                }
			} else if (ins instanceof FuncDefIns) {
				throw new Error("should not happen");
//			    FuncDefIns aIns = (FuncDefIns)ins;
//			    List<UtfplInstruction> newBody = InsLstProcess(aIns.m_body, subMap, aIns.m_name, aIns.m_ret, allFuncs);
//			    FuncDefIns nIns = new FuncDefIns(aIns.m_name, aIns.m_paralst, newBody, aIns.m_ret);
//			    nList.add(nIns);
//			    allFuncs.add(nIns);
			} else if (ins instanceof FuncGroupIns) {
				List<FuncDefIns> fundefLst = new ArrayList<FuncDefIns>();
				for (FuncDefIns aIns: ((FuncGroupIns) ins).m_funLst) {
				    List<UtfplInstruction> newBody = InsLstProcess(aIns.m_body, subMap, aIns.m_name, aIns.m_ret, allFuncs);
				    FuncDefIns nIns = new FuncDefIns(aIns.m_name, aIns.m_paralst, newBody, aIns.m_ret);
				    fundefLst.add(nIns);
				    allFuncs.add(nIns);
				}
				nList.add(new FuncGroupIns(fundefLst));
			    
			} else {
			    throw new Error("Not supported");
			}
		}
		return nList;
		
	}

    /*
//        System.out.println(m_name.getType());
     * Check whether this function has side effect.
     */
    static private Map<FuncDefIns, Boolean> markSideEffectFunLst(List<FuncDefIns> funlst) {
        Map<FuncDefIns, Boolean> fmap = new HashMap<FuncDefIns, Boolean>();
        for (FuncDefIns funDec: funlst) {
            // assume that all user-defined functions have side effect.
            // todo
            fmap.put(funDec, true);
            funDec.flagSideEffect();
        }
        
        return fmap;
        
    }
    
    /*
     * This class is for turning single instruction related to global variables into
     * multiple instructions.
     */
    static class GlobalVarInsProcessor implements InsVisitor {
        List<UtfplInstruction> m_list;
        
        public GlobalVarInsProcessor() {
            m_list = new ArrayList<UtfplInstruction>();
        }
        
        public List<UtfplInstruction> addInsForGlobalVar(List<UtfplInstruction> insLst) {
            for (UtfplInstruction ins: insLst) {
                ins.accept(this);
            }
            return m_list;
        }

        @Override
        public Object visit(CondIns ins) {
            GlobalVarInsProcessor visitorTrue = new GlobalVarInsProcessor();
            List<UtfplInstruction> btrue = visitorTrue.addInsForGlobalVar(ins.m_btrue);
            
            GlobalVarInsProcessor visitorFalse = new GlobalVarInsProcessor();
            List<UtfplInstruction> bfalse = visitorFalse.addInsForGlobalVar(ins.m_bfalse);
            
            CondIns nIns = new CondIns(ins.m_holder, ins.m_cond, btrue, bfalse);
            m_list.add(nIns);
            return m_list;
        }

        @Override
        public Object visit(FuncCallIns ins) {
            if (/*ins.hasSideEffect() && todo */ins.m_holder.isGlobal()) {
                TID temp = TID.createLocalVar(ins.m_funlab + "_tret", ins.getRetType());
                FuncCallIns nCall = new FuncCallIns(temp, ins.m_funlab, ins.m_args);
                MoveIns nMove = new MoveIns(ins.m_holder, temp);
                m_list.add(nCall);
                m_list.add(nMove);
                return m_list;
            } else {
                m_list.add(ins);
                return m_list;
            }

        }

        @Override
        public Object visit(FuncDefIns ins) {
        	throw new Error("should not happen");
//            GlobalVarInsProcessor visitor = new GlobalVarInsProcessor();
//            List<UtfplInstruction> body = visitor.addInsForGlobalVar(ins.m_body);
//            FuncDefIns nIns = new FuncDefIns(ins.m_name, ins.m_paralst, body, ins.m_ret);
//            m_list.add(nIns);
//            return m_list;
        }

        @Override
        public Object visit(MoveIns ins) {
            if (ins.m_vp instanceof TID) {
                TID src = (TID)ins.m_vp;
                if (src.isGlobal() && ins.m_holder.isGlobal()) {
                    TID temp = TID.createLocalVar(src.getID() + "_temp_", PATTypeSingleton.cUnknownType);
                    MoveIns step1 = new MoveIns(temp, src);
                    MoveIns step2 = new MoveIns(ins.m_holder, temp);
                    m_list.add(step1);
                    m_list.add(step2);
                    return m_list;                    
                }
                else {
                    m_list.add(ins);
                    return m_list;
                }
            } else {
                m_list.add(ins);
                return m_list;
            }

        }

		@Override
        public Object visit(FuncGroupIns ins) {
			List<FuncDefIns> fundefLst = new ArrayList<FuncDefIns>();
			
			for (FuncDefIns fundef: ins.m_funLst) {
	            GlobalVarInsProcessor visitor = new GlobalVarInsProcessor();
	            List<UtfplInstruction> body = visitor.addInsForGlobalVar(fundef.m_body);
	            FuncDefIns nIns = new FuncDefIns(fundef.m_name, fundef.m_paralst, body, fundef.m_ret);
	            fundefLst.add(nIns);
			}

            m_list.add(new FuncGroupIns(fundefLst));
            return m_list;
        }
        
    }
}



