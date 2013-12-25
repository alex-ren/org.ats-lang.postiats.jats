package jats.utfpl.csps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jats.utfpl.instruction.AtomValue;
import jats.utfpl.instruction.FunctionInstruction;
import jats.utfpl.instruction.GlobalEntity;
import jats.utfpl.instruction.GlobalValue;
import jats.utfpl.instruction.InsCondAlloc;
import jats.utfpl.instruction.InsCondRelease;
import jats.utfpl.instruction.InsMutexAlloc;
import jats.utfpl.instruction.InsCall;
import jats.utfpl.instruction.InsCond;
import jats.utfpl.instruction.InsFuncDef;
import jats.utfpl.instruction.InsFuncGroup;
import jats.utfpl.instruction.InsLoad;
import jats.utfpl.instruction.InsLoadArray;
import jats.utfpl.instruction.InsMove;
import jats.utfpl.instruction.InsMutexRelease;
import jats.utfpl.instruction.InsRet;
import jats.utfpl.instruction.InsStore;
import jats.utfpl.instruction.InsStoreArray;
import jats.utfpl.instruction.InsThreadCreate;
import jats.utfpl.instruction.InsVisitor;
import jats.utfpl.instruction.ProgramInstruction;
import jats.utfpl.instruction.TID;
import jats.utfpl.instruction.TupleValue;
import jats.utfpl.instruction.UtfplInstruction;
import jats.utfpl.instruction.ValPrim;

public class CSPSTransformer {
    
    /*
     * The transformation includes the following.
     * 1. Group instructions into block. Prepare for the analysis of each values (def / use).
     * 2. Decide whether a value is used out of its visible scope.
     * 3. Add the concept of stack location.
     */
    public ProgramCSPS trans(ProgramInstruction inputProg) {
        Map<TID, VariableInfo> subMap = new HashMap<TID, VariableInfo>();
        
        // ==== handle global variables
        List<VariableInfo> globalVarInfo = new ArrayList<VariableInfo>();
        for (GlobalEntity gEnt: inputProg.getGlobalEntities()) {
            TID tid = gEnt.getTID();
            VariableInfo vi = VariableInfo.createGlobalVariable(tid);
            globalVarInfo.add(vi);
            if (!(gEnt instanceof GlobalValue)) {
                subMap.put(tid, vi);  // global value is defined when initialized.
            }

        }

        // ==== handle main
        TID mainLab = TID.createUserFun("main");
        List<CBlock> body = InsLst2CBlockLst2(inputProg.getInsLst(), subMap, mainLab);
        // add the concept of stack
        processBlockLstForStack(0, body);

        // == handle other functions
        List<FunctionCSPS> procLst = new ArrayList<FunctionCSPS>();
        for (FunctionInstruction func : inputProg.getFuncLst()) {
            int pos = 0;
            FunctionCSPS funcCSP = FunDef2CProcess(func, subMap);
            for (CTempID para: funcCSP.m_paras) {
                pos = para.processStackPrelogue(pos);
            }
            
            // add the concept of stack
            processBlockLstForStack(pos, funcCSP.m_body);
            procLst.add(funcCSP);
        }

        ProgramCSPS outputProg = new ProgramCSPS(globalVarInfo, body, procLst);
        return outputProg;
    }
    
    private static class InsLst2CBlockLstConverter implements InsVisitor {
        private List<CBlock> m_cblkLst;
        private CBEvent m_cbEvt;
        private Map<TID, VariableInfo> m_subMap;
        private TID m_funLab;
        
        public InsLst2CBlockLstConverter(Map<TID, VariableInfo> subMap, TID funLab) {
            m_cblkLst = new ArrayList<CBlock>();
            m_cbEvt = new CBEvent();
            m_subMap = subMap;
            m_funLab = funLab;
        }
        
        public List<CBlock> getCBlockLst() {
            return m_cblkLst;
        }
        
        public void finalize() {
            if (0 != m_cbEvt.size()) {
                m_cblkLst.add(m_cbEvt);
            }
        }

        @Override
        public Object visit(InsCond ins) {
            if (ins.hasSideEffect()) {
                CBCond ccond = new CBCond();
                CTemp ctCond = ValPrim2CTemp(ins.m_cond, m_subMap, m_funLab, ccond);

                Map<TID, VariableInfo> subMapTrue = 
                        new HashMap<TID, VariableInfo>(m_subMap);
                Map<TID, VariableInfo> subMapFalse = 
                        new HashMap<TID, VariableInfo>(m_subMap);

                List<CBlock> btrue = InsLst2CBlockLst2(ins.m_btrue, subMapTrue, m_funLab);
                List<CBlock> bfalse = InsLst2CBlockLst2(ins.m_bfalse, subMapFalse, m_funLab);

                // connect all the links
                ccond.reset(ctCond, btrue, bfalse);

                if (0 != m_cbEvt.size()) {
                    m_cblkLst.add(m_cbEvt);
                    m_cbEvt = new CBEvent();
                }
                m_cblkLst.add(ccond);
                return null;
                
            } else {
                CTemp ctCond = ValPrim2CTemp(ins.m_cond, m_subMap, m_funLab, m_cbEvt);

                // create a CTempID as definition
                CTempID ctHolder = null;
                if (!ins.m_holder.isRet()) {
                	ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                }
                
                Map<TID, VariableInfo> subMap2 = 
                        new HashMap<TID, VariableInfo>(m_subMap);
                
                // This is a hack. Though the InsCond would be turned into a CICond,
                // we need to update its content (TID -> CTempID).
                // save the environment
                Map<TID, VariableInfo> subMapBackup = m_subMap;
                m_subMap = subMap2;
                List<CBlock> blkLstBackup = m_cblkLst;
                List<CInstruction> insLstBackup = m_cbEvt.m_inslst;
                
                m_cblkLst = new ArrayList<CBlock>();
                m_cbEvt.m_inslst = new ArrayList<CInstruction>();

                for (UtfplInstruction bins: ins.m_btrue) {
                    bins.accept(this);
                }
                List<CInstruction> trueBranch = m_cbEvt.m_inslst;
                
                // If the holder of the InsCond is of ret, then the last instruction of
                // the branch would be InsRet. This will trigger m_cbEvt to
                // included in m_cblkLst.
                if (trueBranch.size() < 1) {
                	CBEvent cbEvt = (CBEvent)m_cblkLst.get(0);
                	trueBranch = cbEvt.m_inslst;
                }
                
                m_cblkLst = new ArrayList<CBlock>();
                m_cbEvt.m_inslst = new ArrayList<CInstruction>();

                for (UtfplInstruction bins: ins.m_bfalse) {
                    bins.accept(this);
                }
                List<CInstruction> falseBranch = m_cbEvt.m_inslst;
                
                // If the holder of the InsCond is of ret, then the last instruction of
                // the branch would be InsRet. This will trigger m_cbEvt to
                // included in m_cblkLst.
                if (falseBranch.size() < 1) {
                	CBEvent cbEvt = (CBEvent)m_cblkLst.get(0);
                	falseBranch = cbEvt.m_inslst;
                }
                
                // recover the environment
                m_subMap = subMapBackup;
                m_cbEvt.m_inslst = insLstBackup;
                m_cblkLst = blkLstBackup;
                
                CICond nIns = new CICond(ctHolder/* may be null*/, ctCond, trueBranch, falseBranch, m_cbEvt);
                if (!ins.m_holder.isRet() && ctHolder.isDefinition()) {
//                    System.out.println("dddddddddddddddddd holder is " + ins.m_holder + "in " + m_funLab);
                    CIVarDef defIns = new CIVarDef(ctHolder, m_cbEvt);
                    m_cbEvt.add(defIns);  // add an extra instruction to hold the value caused by the "if"
                }
                
                m_cbEvt.add(nIns);

                if (ins.m_holder.isRet()) {
                    m_cblkLst.add(m_cbEvt);
                    m_cbEvt = new CBEvent();
                }
                return null;
            }
        }

        @Override
        public Object visit(InsCall ins) {
            if (ins.hasSideEffect()) {
                List<CTemp> nLst = ValPrimLst2CTempLst(ins.m_args, m_subMap, m_funLab, m_cbEvt);
                CIProcCallPrelogue cCallPre = new CIProcCallPrelogue(m_cbEvt, nLst, ins.m_isTailCall);
                m_cbEvt.add(cCallPre);
                m_cblkLst.add(m_cbEvt);
                m_cbEvt = new CBEvent();
                
                CBProc cprocess = new CBProc(ins.m_funlab, ins.m_isTailCall);
                m_cblkLst.add(cprocess);
                
                // No epilogue at all.
                if (!ins.m_holder.isRet()) {
                    CTempID ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                    CIProcCallEpilog cCallEpi = new CIProcCallEpilog(m_cbEvt, ins.m_funlab, ctHolder);
                    m_cbEvt.add(cCallEpi);
                } 

                return null;

            } else {
                List<CTemp> nLst = ValPrimLst2CTempLst(ins.m_args, m_subMap, m_funLab, m_cbEvt);
                CTempID ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIFunCall nIns = new CIFunCall(ins.m_funlab, nLst, ctHolder, ins.m_isTailCall, m_cbEvt);

                m_cbEvt.add(nIns);

                // Add return ins
                if (ins.m_holder.isRet()) {
                    CTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                    CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                    m_cbEvt.add(retIns);
                    m_cblkLst.add(m_cbEvt);
                    m_cbEvt = new CBEvent();
                }
                return null;
            }
        }

        @Override
        public Object visit(InsFuncDef ins) {
            throw new Error("should not happen");
        }

        @Override
        public Object visit(InsFuncGroup ins) {
            throw new Error("should not happen");
        }

        @Override
        public Object visit(InsStoreArray ins) {

            CTemp localValue = ValPrim2CTemp(ins.m_localValue, m_subMap, m_funLab, m_cbEvt);
            CTempID globalVar = TID2CTempID(ins.m_globalVar, m_subMap, m_funLab, m_cbEvt);
            CTemp localIndex = ValPrim2CTemp(ins.m_localIndex, m_subMap, m_funLab, m_cbEvt);
            
            CIStoreArray nIns = new CIStoreArray(localValue, globalVar, localIndex, m_cbEvt);
            m_cbEvt.add(nIns);
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new CBEvent();
            
            return null;            
        }

        @Override
        public Object visit(InsStore ins) {

            CTemp localSrc = ValPrim2CTemp(ins.m_localSrc, m_subMap, m_funLab, m_cbEvt);
            CTempID globalDest = TID2CTempID(ins.m_globalDest, m_subMap, m_funLab, m_cbEvt);
            
            CIStore nIns = new CIStore(localSrc, globalDest, m_cbEvt);
            m_cbEvt.add(nIns);
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new CBEvent();
            
            return null; 
        }
        

        @Override
        public Object visit(InsLoad ins) {
            
            CTempID globalVar = TID2CTempID(ins.m_globalVar, m_subMap, m_funLab, m_cbEvt);
            CTempID localHolder = TID2CTempID(ins.m_localHolder, m_subMap, m_funLab, m_cbEvt);
            
            CILoad nIns = new CILoad(globalVar, localHolder, m_cbEvt);
            m_cbEvt.add(nIns);
            
            // Add return ins
            if (ins.m_localHolder.isRet()) {
                CTempID retCTempID = TID2CTempID(ins.m_localHolder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
            }
            
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new CBEvent();
            
            return null; 
        }

        @Override
        public Object visit(InsRet ins) {
            CTemp v = ValPrim2CTemp(ins.m_v, m_subMap, m_funLab, m_cbEvt);
                    
            CIReturn nIns = new CIReturn(v, m_cbEvt);
            m_cbEvt.add(nIns);
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new CBEvent();

            return null;
        }

        @Override
        public Object visit(InsLoadArray ins) {

            CTempID localHolder = TID2CTempID(ins.m_localHolder, m_subMap, m_funLab, m_cbEvt);
            CTempID globalVar = TID2CTempID(ins.m_globalVar, m_subMap, m_funLab, m_cbEvt);
            CTemp localIndex = ValPrim2CTemp(ins.m_localIndex, m_subMap, m_funLab, m_cbEvt);
            
            CILoadArray nIns = new CILoadArray(globalVar, localIndex, localHolder, m_cbEvt);
            m_cbEvt.add(nIns);
            
            // Add return ins
            if (ins.m_localHolder.isRet()) {
                CTempID retCTempID = TID2CTempID(ins.m_localHolder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
            }
            
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new CBEvent();
            
            return null;    
            
        }


        @Override
        public Object visit(InsMove ins) {
            CTemp v = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
            CTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
			CIMove nIns = new CIMove(v, holder, m_cbEvt);
			m_cbEvt.add(nIns);

            return null;
        }

		@Override
        public Object visit(InsThreadCreate ins) {
			CBThreadCreate nBlk = new CBThreadCreate(ins.m_funlab);
			
	        CTemp tid = ValPrim2CTemp(ins.m_tid, m_subMap, m_funLab, nBlk);
	        CTemp args = ValPrim2CTemp(ins.m_args, m_subMap, m_funLab, nBlk);
	        
	        nBlk.setContent(tid, args);
	        
            if (0 != m_cbEvt.size()) {
                m_cblkLst.add(m_cbEvt);
                m_cbEvt = new CBEvent();
            }
            m_cblkLst.add(nBlk);
            return null;
        }

        @Override
        public Object visit(InsMutexAlloc ins) {
            CTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            
            CIMutexAlloc nIns = new CIMutexAlloc(holder, m_cbEvt);
            m_cbEvt.add(nIns);
            
            // Add return ins
            if (ins.m_holder.isRet()) {
                CTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
            }
            
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new CBEvent();
            
            return null;
        }
        
		@Override
        public Object visit(InsMutexRelease ins) {
			CTemp mutex = ValPrim2CTemp(ins.m_mutex, m_subMap, m_funLab, m_cbEvt);
			CIMutexRelease nIns = new CIMutexRelease(m_cbEvt, mutex);
            m_cbEvt.add(nIns);
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new CBEvent();
            return null;
        }
		

        @Override
        public Object visit(InsCondAlloc ins) {
            CTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            
            CICondAlloc nIns = new CICondAlloc(holder, m_cbEvt);
            m_cbEvt.add(nIns);
            
            // Add return ins
            if (ins.m_holder.isRet()) {
                CTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
            }
            
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new CBEvent();
            
            return null;
        }
        
		@Override
        public Object visit(InsCondRelease ins) {
			CTemp cond = ValPrim2CTemp(ins.m_cond, m_subMap, m_funLab, m_cbEvt);
			CICondRelease nIns = new CICondRelease(m_cbEvt, cond);
            m_cbEvt.add(nIns);
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new CBEvent();
            return null;
        }
    }

    static private List<CBlock> InsLst2CBlockLst2(List<UtfplInstruction> insLst, Map<TID, VariableInfo> subMap, TID funLab)
    {
        InsLst2CBlockLstConverter cvt = new InsLst2CBlockLstConverter(subMap, funLab);
        for (UtfplInstruction ins: insLst) {
            ins.accept(cvt);
        }
        
        cvt.finalize();
        return cvt.getCBlockLst();
    }
    
    /*
     * The processing includes the following.
     * 1. Decide whether a value is used out of its visible scope.
     * 2. Add the concept of stack location according to the analysis result of 1.
     *    After this, there would be no identical CTempID at all.
     */
    static private void processBlockLstForStack(int offset, List<CBlock> blkLst) {
        for (CBlock blk: blkLst) {
            offset = blk.process(offset);            
        }
    }
    
    /*
     * Get an object of CTemp from an object of ValPrim.
     * "map" may be updated.
     * "grp" is the group which holds the object of CTemp.
     */
    static private CTemp ValPrim2CTemp(ValPrim vp, Map<TID, VariableInfo> map, TID funLab, CBlock grp) {
        if (vp instanceof AtomValue) {
            return new CTempVal((AtomValue) vp);
        } else if (vp instanceof TID) {
            TID tid = (TID)vp;
            return TID2CTempID(tid, map, funLab, grp);
        } else if (vp instanceof TupleValue) {
            return new CTempVal((TupleValue) vp);
        } else {
            throw new Error("shall not happen");
        }
    }
    
    /*
     * Create a new list of CTemp from a list of ValPrim.
     */
    static private List<CTemp> ValPrimLst2CTempLst(List<ValPrim> vpLst, Map<TID, VariableInfo> map, TID funLab, CBlock grp) {
    	List<CTemp> nLst = new ArrayList<CTemp>();
    	for (ValPrim vp: vpLst) {
    		CTemp newVp = ValPrim2CTemp(vp, map, funLab, grp);
    		nLst.add(newVp);
    	}
    	return nLst;
    }
    
    /*
     * If "VariableInfo" is not in the map, then it's a definition. 
     * Otherwise, it's a usage.
     */
    static private CTempID TID2CTempID(TID tid, Map<TID, VariableInfo> map, TID funLab, CBlock grp) {
        EntityLocation loc = EntityLocation.create(funLab, grp);
        CTempID ret = null;
        if (tid == TID.ANONY) {
        	VariableInfo vi = VariableInfo.create(tid, loc);
        	// Do not put into map.
            ret = CTempID.createAsDef(vi, loc);
            return ret;
        }
        
        VariableInfo vi = map.get(tid);
        if (null == vi) {
            if (tid.isGlobalVariable()) {
                throw new Error("Wong usage. Only for global value, parameter, and local variable");
            }
            vi = VariableInfo.create(tid, loc);
            map.put(tid, vi);  // This is important.
            ret = CTempID.createAsDef(vi, loc);
        } else {
            vi.addUsage(loc);
            ret = CTempID.createAsUsage(vi, loc);
        }
        return ret;
    }
    
//    /*
//     * translate UtfplInstruction's into a preliminary form of CSPS instructions
//     * Turning ValPrim into CTemp, or in essence turning TID into CTempID. 
//     * The identity of each CTempID is important. It's possible that two different
//     * CTempID has the same TID in the case for CCondBlock.
//     * subMap: inout
//     * outProcs: output
//     *   The new functions defined in the input "inslst" will be put into the list.
//     */
//    static private List<CBlock> InsLst2CBlockLst(List<UtfplInstruction> inslst, 
//                                         Map<TID, VariableInfo> subMap,
//                                         TID funLab,
//                                         List<FunctionCSPS> outProcs,
//                                         int level
//                                         ) {
//
//        List<CBlock> insBlockList = new ArrayList<CBlock>();
//        CBEvent cblock = new CBEvent(level);
//        
//        for (UtfplInstruction ins: inslst) {
//            if (ins instanceof MoveIns) {
//                MoveIns aIns = (MoveIns)ins;
//                CTempID ctHolder = TID2CTempID(aIns.m_holder, subMap, funLab, cblock, level);
//                CTemp ctValue = ValPrim2CTemp(aIns.m_vp, subMap, funLab, cblock, level);
//                CIMove nIns = new CIMove(ctHolder, ctValue, cblock);
//                cblock.add(nIns);
//                
//                // Add return ins
//                if (aIns.m_holder.isRet()) {
//                    CTempID retCTempID = TID2CTempID(aIns.m_holder, subMap, funLab, cblock, level);
//                    CIReturn retIns = new CIReturn(retCTempID, cblock);
//                    cblock.add(retIns);       
//                }
//                
//                if (aIns.hasSideEffect()) {
//                    insBlockList.add(cblock);
//                    cblock = new CBEvent(level);
//                } else {
//                    continue;
//                }
//            } else if (ins instanceof FuncCallIns) {
//                FuncCallIns aIns = (FuncCallIns)ins;
//                if (aIns.hasSideEffect()) {
//                    CBProc cprocess = new CBProc(aIns.m_funlab, level);
//
//                	List<CTemp> nLst = ValPrimLst2CTempLst(aIns.m_args, subMap, funLab, cprocess, level);
//                	CTempID ctHolder = TID2CTempID(aIns.m_holder, subMap, funLab, cprocess, level);
//                    cprocess.reset(nLst, ctHolder);
//                    
//                    if (0 != cblock.size()) {
//                        insBlockList.add(cblock);
//                        cblock = new CBEvent(level);
//                    }
//                    insBlockList.add(cprocess);
//
//                } else {
//                	List<CTemp> nLst = ValPrimLst2CTempLst(aIns.m_args, subMap, funLab, cblock, level);
//                	CTempID ctHolder = TID2CTempID(aIns.m_holder, subMap, funLab, cblock, level);
//                    CIFunCall nIns = new CIFunCall(aIns.m_funlab, nLst, ctHolder, cblock);
//
//                    cblock.add(nIns);
//                    
//                    // Add return ins
//                    if (aIns.m_holder.isRet()) {
//                        CTempID retCTempID = TID2CTempID(aIns.m_holder, subMap, funLab, cblock, level);
//                        CIReturn retIns = new CIReturn(retCTempID, cblock);
//                        cblock.add(retIns);
//                    }
//                }
//            } else if (ins instanceof InsCond) {
//                InsCond aIns = (InsCond)ins;
//                if (ins.hasSideEffect()) {
//                    CBCond ccond = new CBCond(level);
//                    CTemp ctCond = ValPrim2CTemp(aIns.m_cond, subMap, funLab, ccond, level);
//                	
//                	Map<TID, VariableInfo> subMapTrue = new HashMap<TID, VariableInfo>(subMap);
//                	Map<TID, VariableInfo> subMapFalse = new HashMap<TID, VariableInfo>(subMap);
//                	
//                    List<CBlock> btrue = InsLst2CBlockLst(aIns.m_btrue, subMapTrue, funLab, outProcs, level);
//                    List<CBlock> bfalse = InsLst2CBlockLst(aIns.m_bfalse, subMapFalse, funLab, outProcs, level);
//                    
//                    // connect all the links
//                    ccond.reset(ctCond, btrue, bfalse);
//                    
//                    if (0 != cblock.size()) {
//                        insBlockList.add(cblock);
//                        cblock = new CBEvent(level);
//                    }
//                    insBlockList.add(ccond);
//                    
//                } else {
//                    throw new Error("todo");
//                }
//            } else if (ins instanceof InsFuncDef) {
//                InsFuncDef aIns = (InsFuncDef)ins;
//                if (ins.hasSideEffect()) {
//                    Map<TID, VariableInfo> innerSubMap = new HashMap<TID, VariableInfo>(subMap);
//                    List<FunctionCSPS> innerProcs = new ArrayList<FunctionCSPS>();
//                    FunctionCSPS cProc = FunDef2CProcess(aIns, innerSubMap, innerProcs, cblock, level + 1);
//                    cblock.add(cProc);
//                    
//                    outProcs.add(cProc);
//                    outProcs.addAll(innerProcs);
//                } else {
//                    throw new Error("todo");
//                }
////            } else if (ins instanceof ReturnIns) {
////                ReturnIns aIns = (ReturnIns)ins;
////                CTempID ctHolder = TID2CTempID(aIns.m_tid, subMap, funLab, cblock, level);
////                CIReturn nIns = new CIReturn(ctHolder, cblock);
////                cblock.add(nIns);
////                
//            } else {
//            	throw new Error("no such case");
//            	
//            }
//        }
//        
//        if (0 != cblock.size()) {
//            insBlockList.add(cblock);
//        }
//        return insBlockList;
//    }
    
    static private FunctionCSPS FunDef2CProcess(
            FunctionInstruction funDef
            , Map<TID, VariableInfo> subMap) {
        List<CTempID> paras = new ArrayList<CTempID>();
        for (TID para: funDef.getParaLst()) {
            CTempID cPara = TID2CTempID(para, subMap, funDef.getName(), null /* CBlock is null */);
            paras.add(cPara);
        }
        for (TID para: funDef.getEscParaLst()) {
            CTempID cPara = TID2CTempID(para, subMap, funDef.getName(), null /* CBlock is null */);
            paras.add(cPara);
        }
        
        List<CBlock> body = InsLst2CBlockLst2(funDef.getBody(), subMap, funDef.getName());
        FunctionCSPS cProc = new FunctionCSPS(funDef.getName(), paras, body);
        return cProc;
    }
}




