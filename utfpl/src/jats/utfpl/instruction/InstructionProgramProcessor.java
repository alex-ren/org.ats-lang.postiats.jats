package jats.utfpl.instruction;

import jats.utfpl.patcsps.type.PATTypeFunc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

// handle "if" branch
public class InstructionProgramProcessor {
    
    /*
     * The content of process includes the following.
     * Transformation of "if" branch.
     * Effect analysis.
     */
    static public ProgramInstruction processProgram(ProgramInstruction inputProg) {

        for (FunctionInstruction func: inputProg.getFuncLst()) {
            // todo
            // All user-defined functions have effect.
            ((PATTypeFunc)func.getName().getType()).updateEffect(true);
        }
        
        for (FunctionInstruction func: inputProg.getFuncLst()) {
            EffectAnalyze(func.getBody());
        }
        
        EffectAnalyze(inputProg.getInsLst());       

        // ============================================
        
        List<UtfplInstruction> nBody = InsLstProcess(inputProg.getInsLst(), null);
        List<FunctionInstruction> nFuncLst = new ArrayList<FunctionInstruction>();
        
        for (FunctionInstruction func: inputProg.getFuncLst()) {
            nFuncLst.add(processFunction(func));
        }
        
        ProgramInstruction nProg = new ProgramInstruction(inputProg.getGlobalEntities(), nBody, nFuncLst);
        
        return nProg;        
    }
    
    
    /*
     * Update the effect of "if" instruction.
     */
    static private boolean EffectAnalyze(List<UtfplInstruction> insLst) {
        boolean ret = false;
        for (UtfplInstruction ins: insLst) {
            if (ins instanceof InsCond) {
                boolean btrue = EffectAnalyze(((InsCond)ins).m_btrue);
                boolean bfalse = EffectAnalyze(((InsCond)ins).m_bfalse);
                if (btrue || bfalse) {
                    ret = true;
                    ((InsCond)ins).setEffectFlag(true);
                } else {
                    ((InsCond)ins).setEffectFlag(false);
                }
            } else {
                if (ins.hasSideEffect()) {
                    ret = true;
                }
            }
        }
        return ret;
    }

    static public FunctionInstruction processFunction(FunctionInstruction inputFunc) {
        List<UtfplInstruction> nBody = InsLstProcess(inputFunc.getBody(), null);
        
        FunctionInstruction nFunc = 
                new FunctionInstruction(
                        inputFunc.getName(),
                        inputFunc.getParaLst(),
                        inputFunc.getEscParaLst(),
                        nBody
                        );
        
        return nFunc;

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
    
    static class InstructionProcessor implements InsVisitor {
        private Map<TID, TID> m_subMap;
        
        public InstructionProcessor(Map<TID, TID> subMap) {
            m_subMap = subMap;
        }

        @Override
        public Object visit(InsCond ins) {
            throw new Error("shall not happen");
        }

        @Override
        public Object visit(InsCall ins) {
            if (null == m_subMap) {
                return ins;
            } else {
                TID nHolder = ins.m_holder.dup();
                m_subMap.put(ins.m_holder, nHolder);
                List<ValPrim> nArgLst = subsVPLst(ins.m_args, m_subMap);                
                
                return new InsCall(nHolder, ins.m_funlab, nArgLst, ins.m_isTailCall);
            }
        }

        @Override
        public Object visit(InsFuncDef ins) {
            throw new Error("shall not happen");
        }

        @Override
        public Object visit(InsFuncGroup ins) {
            throw new Error("shall not happen");
        }

        @Override
        public Object visit(InsStoreArray ins) {
            if (null == m_subMap) {
                return ins;
            } else {
                ValPrim nLocalIndex = subsVP(ins.m_localIndex, m_subMap); 
                ValPrim nLocalValue = subsVP(ins.m_localValue, m_subMap);
                
                return new InsStoreArray(nLocalValue, ins.m_globalVar, nLocalIndex);
            }
        }

        @Override
        public Object visit(InsStore ins) {
            if (null == m_subMap) {
                return ins;
            } else {
                ValPrim nLocalSrc = subsVP(ins.m_localSrc, m_subMap);
                
                return new InsStore(nLocalSrc, ins.m_globalDest);
            }
        }

        @Override
        public Object visit(InsRet ins) {
            if (null == m_subMap) {
                return ins;
            } else {
                ValPrim nV = subsVP(ins.m_v, m_subMap);
                
                return new InsRet(nV);
            }
        }

        @Override
        public Object visit(InsLoadArray ins) {
            if (null == m_subMap) {
                return ins;
            } else {
                ValPrim nLocalIndex = subsVP(ins.m_localIndex, m_subMap); 
                
                TID nLocalHolder = ins.m_localHolder.dup();
                m_subMap.put(ins.m_localHolder, nLocalHolder);
                
                return new InsLoadArray(ins.m_globalVar, nLocalIndex, nLocalHolder);
            }
        }

        @Override
        public Object visit(InsAllocMutex ins) {
            if (null == m_subMap) {
                return ins;
            } else {
                TID nHolder = ins.m_holder.dup();
                m_subMap.put(ins.m_holder, nHolder);
                
                return new InsAllocMutex(nHolder);
            }
        }

        @Override
        public Object visit(InsMove ins) {
            if (null == m_subMap) {
                return ins;
            } else {
                ValPrim nVP = subsVP(ins.m_vp, m_subMap); 
                
                TID nHolder = ins.m_holder.dup();
                m_subMap.put(ins.m_holder, nHolder);
                
                return new InsMove(nVP, nHolder);
            }
        }

        @Override
        public Object visit(InsLoad ins) {
            if (null == m_subMap) {
                return ins;
            } else {
                TID nLocalHolder = ins.m_localHolder.dup();
                m_subMap.put(ins.m_localHolder, nLocalHolder);
                
                return new InsLoad(ins.m_globalVar, nLocalHolder);
            }
        }
    }
    
    /*
     * Create a new list and the old list is unchanged.
     * The purpose of this function is to process if-branch -- turn the merged
     * "if" into branches.
     * 
     * subMap means two things if it's not null:
     *   1. we need to generate new TID for dest.
     *   2. we need to substitute for the src.
     * 
     * If it's null, then do thing.
     * 
     */
    static private List<UtfplInstruction> InsLstProcess(
            List<UtfplInstruction> insLst, 
            Map<TID, TID> subMap) {
        List<UtfplInstruction> nList = new ArrayList<UtfplInstruction>();
        ListIterator<UtfplInstruction> iter = insLst.listIterator();
        
        InstructionProcessor processor = new InstructionProcessor(subMap);
        
        while (iter.hasNext()) {
            UtfplInstruction ins = iter.next();
            if (ins instanceof InsCond) {
                InsCond cIns = (InsCond)ins;
                List<UtfplInstruction> restList = insLst.subList(iter.nextIndex(), insLst.size());
                
                if (cIns.hasSideEffect()) {
                    if (null == subMap) {
                        List<UtfplInstruction> trueList = new ArrayList<UtfplInstruction>(cIns.m_btrue);
                        trueList.addAll(restList);
                        List<UtfplInstruction> trueBranch = InsLstProcess(trueList, null);
                        
                        Map<TID, TID> subMapFalse = new HashMap<TID, TID>();
                        List<UtfplInstruction> falseList = new ArrayList<UtfplInstruction>(cIns.m_bfalse);
                        falseList.addAll(restList);
                        List<UtfplInstruction> falseBranch = InsLstProcess(falseList, subMapFalse);
                        
                        InsCond nCondIns = new InsCond(null, cIns.m_cond, trueBranch, falseBranch, cIns.hasSideEffect());
                        nList.add(nCondIns);
                        return nList;  // We can return since "if" is the last instruction.
                    } else {
                        Map<TID, TID> subMapTrue = new HashMap<TID, TID>(subMap);
                        List<UtfplInstruction> trueList = new ArrayList<UtfplInstruction>(cIns.m_btrue);
                        trueList.addAll(restList);
                        List<UtfplInstruction> trueBranch = InsLstProcess(trueList, subMapTrue);
                        
                        Map<TID, TID> subMapFalse = new HashMap<TID, TID>(subMap);
                        List<UtfplInstruction> falseList = new ArrayList<UtfplInstruction>(cIns.m_bfalse);
                        falseList.addAll(restList);
                        List<UtfplInstruction> falseBranch = InsLstProcess(falseList, subMapFalse);
                        
                        ValPrim nCond = subsVP(cIns.m_cond, subMap);
                        InsCond nCondIns = new InsCond(null, nCond, trueBranch, falseBranch, cIns.hasSideEffect());
                        nList.add(nCondIns);
                        return nList;  // We can return since "if" is the last instruction.
                    }
                } else {
                    if (null == subMap) {
                        List<UtfplInstruction> trueBranch = InsLstProcess(cIns.m_btrue, null);
                        List<UtfplInstruction> falseBranch = InsLstProcess(cIns.m_bfalse, null);
                        InsCond nCondIns = new InsCond(cIns.m_holder, cIns.m_cond, trueBranch, falseBranch, cIns.hasSideEffect());
                        nList.add(nCondIns);
                    } else {
                        Map<TID, TID> subMap2 = new HashMap<TID, TID>(subMap);
                        List<UtfplInstruction> trueBranch = InsLstProcess(cIns.m_btrue, subMap2);
                        List<UtfplInstruction> falseBranch = InsLstProcess(cIns.m_bfalse, subMap2);
                        InsCond nCondIns = new InsCond(cIns.m_holder, cIns.m_cond, trueBranch, falseBranch, cIns.hasSideEffect());
                        nList.add(nCondIns);
                    }
                }
            } else {
                Object retIns = ins.accept(processor);
                if (null != retIns) {
                    UtfplInstruction nIns = (UtfplInstruction)retIns;
                    nList.add(nIns);
                }

            }
        }
        return nList;
    }


//	/*
//	 * Create a new list and the old list is unchanged.
//	 * Substitution is done along the way as well. Another way to put it,
//	 * the true instruction list is based on both "insLst" and "subMap".
//	 * 
//	 * The purpose of this function is to process if-branch -- turn the program
//	 * into a "Static Single Assignment" form.
//	 */
//    /*
//     * Effect analysis is done after this stage.
//     */
//	static private List<UtfplInstruction> InsLstProcess(
//			List<UtfplInstruction> insLst, 
//			Map<TID, TID> subMap,
//			TID FuncLab,
//			TID retHolder,
//			List<InsFuncDef> allFuncs) {
//		
//		List<UtfplInstruction> nList = new ArrayList<UtfplInstruction>();
//		
//		ListIterator<UtfplInstruction> iter = insLst.listIterator();
//		while (iter.hasNext()) {
//			UtfplInstruction ins = iter.next();
//			if (ins instanceof MoveIns) {
//                MoveIns aIns = (MoveIns)ins;
//                MoveIns nIns = aIns.createSubs(subMap);
//                nList.add(nIns);
////                if (nIns.m_holder.isRet()) {
////                    nList.add(new ReturnIns(nIns.m_holder));
////                }
//			} else if (ins instanceof InsCond) {
//				InsCond aIns = (InsCond)ins;
//				
//				TID condRetHolder = TID.subsTID(aIns.m_holder, subMap);
////				System.out.println("cond holder is " + condRetHolder);
//				ValPrim aCond = subsVP(aIns.m_cond, subMap);
//
//                // copy the list
//                List<UtfplInstruction> insLstTrue = aIns.m_btrue;
//                List<UtfplInstruction> insLstFalse = aIns.m_bfalse;
//                
//                
//				if (iter.hasNext() && // has next instruction, which is not "ReturnIns"
//                        !aIns.m_holder.isGlobal()) { // Global variable as the holder, no need to merge.
//                    // Since aIns is not the last instruction, aRetHolder != retHolder.
//
//                    // The instructions afterwards.
//                    int nextInd = iter.nextIndex();
//                    List<UtfplInstruction> restLst = insLst.subList(nextInd,
//                            insLst.size());
//
//                    // The new function is actually a closure with only one parameter.
//                    List<TID> newParas = new ArrayList<TID>();
//
//                    TID aPara = TID.createPara(condRetHolder.getID(), false);
//                    newParas.add(aPara);
//
//                    HashMap<TID, TID> newSubMap = new HashMap<TID, TID>(subMap);
//                    // replace the new parameter into the following
//                    // instructions
//                    newSubMap.put(condRetHolder, aPara); // <===== map 1
//
//                    TID newFuncLab = TID.createUserFun(FuncLab.getID() + "_if");
//
//                    TID newRetHolder = null;
//                    if (null != retHolder) {
//                        newRetHolder = TID.createRetHolder(FuncLab.getID()
//                                + "_ret");
//                    } else {
//                        throw new Error("check this");
//                    }
//                    newSubMap.put(retHolder, newRetHolder); // <====== map 2
//
//                    List<UtfplInstruction> newFuncBody = InsLstProcess(restLst,
//                            newSubMap, newFuncLab, newRetHolder, allFuncs);
//
//                    InsFuncDef newFuncDef = new InsFuncDef(newFuncLab,
//                            newParas, newFuncBody, newRetHolder);
//                    nList.add(newFuncDef); // maybe a closure
//                    allFuncs.add(newFuncDef);
//
//                    List<ValPrim> newArgs = new ArrayList<ValPrim>();
//                    newArgs.add(condRetHolder);
//                    FuncCallIns newFuncCall = new FuncCallIns(retHolder,
//                            newFuncLab, newArgs);
//
//                    // copy the list
//                    insLstTrue = new ArrayList<UtfplInstruction>(aIns.m_btrue);
//                    insLstFalse = new ArrayList<UtfplInstruction>(aIns.m_bfalse);
//
//                    insLstTrue.add(newFuncCall);
//                    insLstFalse.add(newFuncCall);
//				}
//				insLstTrue = InsLstProcess(insLstTrue, subMap, FuncLab, retHolder, allFuncs);
//				insLstFalse = InsLstProcess(insLstFalse, subMap, FuncLab, retHolder, allFuncs);
//                
//                InsCond nIns = new InsCond(retHolder, aCond, insLstTrue, insLstFalse);
//                nList.add(nIns);
//                break;  // done, no more instructions
//			} else if (ins instanceof FuncCallIns) {
//			    FuncCallIns aIns = (FuncCallIns)ins;
//			    FuncCallIns nIns = aIns.createSubs(subMap);
//			    nList.add(nIns);
////                if (nIns.m_holder.isRet()) {
////                    nList.add(new ReturnIns(nIns.m_holder));
////                }
//			} else if (ins instanceof InsFuncDef) {
//				throw new Error("should not happen");
////			    FuncDefIns aIns = (FuncDefIns)ins;
////			    List<UtfplInstruction> newBody = InsLstProcess(aIns.m_body, subMap, aIns.m_name, aIns.m_ret, allFuncs);
////			    FuncDefIns nIns = new FuncDefIns(aIns.m_name, aIns.m_paralst, newBody, aIns.m_ret);
////			    nList.add(nIns);
////			    allFuncs.add(nIns);
//			} else if (ins instanceof InsFuncGroup) {
//				List<InsFuncDef> fundefLst = new ArrayList<InsFuncDef>();
//				for (InsFuncDef aIns: ((InsFuncGroup) ins).m_funLst) {
//				    List<UtfplInstruction> newBody = InsLstProcess(aIns.m_body, subMap, aIns.m_name, aIns.m_ret, allFuncs);
//				    InsFuncDef nIns = new InsFuncDef(aIns.m_name, aIns.m_paralst, newBody, aIns.m_ret);
//				    fundefLst.add(nIns);
//				    allFuncs.add(nIns);
//				}
//				nList.add(new InsFuncGroup(fundefLst));
//			    
//			} else {
//			    throw new Error("Not supported");
//			}
//		}
//		return nList;
//		
//	}

//    /*
////        System.out.println(m_name.getType());
//     * Check whether this function has side effect.
//     */
//    static private Map<InsFuncDef, Boolean> markSideEffectFunLst(List<InsFuncDef> funlst) {
//        Map<InsFuncDef, Boolean> fmap = new HashMap<InsFuncDef, Boolean>();
//        for (InsFuncDef funDec: funlst) {
//            // assume that all user-defined functions have side effect.
//            // todo
//            fmap.put(funDec, true);
//            funDec.flagSideEffect();
//        }
//        
//        return fmap;
//        
//    }
//    
//    /*
//     * This class is for turning single instruction related to global variables into
//     * multiple instructions.
//     */
//    static class GlobalVarInsProcessor implements InsVisitor {
//        List<UtfplInstruction> m_list;
//        
//        public GlobalVarInsProcessor() {
//            m_list = new ArrayList<UtfplInstruction>();
//        }
//        
//        public List<UtfplInstruction> addInsForGlobalVar(List<UtfplInstruction> insLst) {
//            for (UtfplInstruction ins: insLst) {
//                ins.accept(this);
//            }
//            return m_list;
//        }
//
//        @Override
//        public Object visit(InsCond ins) {
//            GlobalVarInsProcessor visitorTrue = new GlobalVarInsProcessor();
//            List<UtfplInstruction> btrue = visitorTrue.addInsForGlobalVar(ins.m_btrue);
//            
//            GlobalVarInsProcessor visitorFalse = new GlobalVarInsProcessor();
//            List<UtfplInstruction> bfalse = visitorFalse.addInsForGlobalVar(ins.m_bfalse);
//            
//            InsCond nIns = new InsCond(ins.m_holder, ins.m_cond, btrue, bfalse);
//            m_list.add(nIns);
//            return m_list;
//        }
//
//        @Override
//        public Object visit(FuncCallIns ins) {
//            if (/*ins.hasSideEffect() && todo */ins.m_holder.isGlobal()) {
//                TID temp = TID.createLocalVar(ins.m_funlab + "_tret", ins.getRetType());
//                FuncCallIns nCall = new FuncCallIns(temp, ins.m_funlab, ins.m_args);
//                MoveIns nMove = new MoveIns(ins.m_holder, temp);
//                m_list.add(nCall);
//                m_list.add(nMove);
//                return m_list;
//            } else {
//                m_list.add(ins);
//                return m_list;
//            }
//
//        }
//
//        @Override
//        public Object visit(InsFuncDef ins) {
//        	throw new Error("should not happen");
////            GlobalVarInsProcessor visitor = new GlobalVarInsProcessor();
////            List<UtfplInstruction> body = visitor.addInsForGlobalVar(ins.m_body);
////            FuncDefIns nIns = new FuncDefIns(ins.m_name, ins.m_paralst, body, ins.m_ret);
////            m_list.add(nIns);
////            return m_list;
//        }
//
//        @Override
//        public Object visit(MoveIns ins) {
//            if (ins.m_vp instanceof TID) {
//                TID src = (TID)ins.m_vp;
//                if (src.isGlobal() && ins.m_holder.isGlobal()) {
//                    TID temp = TID.createLocalVar(src.getID() + "_temp_", PATTypeSingleton.cUnknownType);
//                    MoveIns step1 = new MoveIns(temp, src);
//                    MoveIns step2 = new MoveIns(ins.m_holder, temp);
//                    m_list.add(step1);
//                    m_list.add(step2);
//                    return m_list;                    
//                }
//                else {
//                    m_list.add(ins);
//                    return m_list;
//                }
//            } else {
//                m_list.add(ins);
//                return m_list;
//            }
//
//        }
//
//		@Override
//        public Object visit(InsFuncGroup ins) {
//			List<InsFuncDef> fundefLst = new ArrayList<InsFuncDef>();
//			
//			for (InsFuncDef fundef: ins.m_funLst) {
//	            GlobalVarInsProcessor visitor = new GlobalVarInsProcessor();
//	            List<UtfplInstruction> body = visitor.addInsForGlobalVar(fundef.m_body);
//	            InsFuncDef nIns = new InsFuncDef(fundef.m_name, fundef.m_paralst, body, fundef.m_ret);
//	            fundefLst.add(nIns);
//			}
//
//            m_list.add(new InsFuncGroup(fundefLst));
//            return m_list;
//        }
//        
//    }
}



