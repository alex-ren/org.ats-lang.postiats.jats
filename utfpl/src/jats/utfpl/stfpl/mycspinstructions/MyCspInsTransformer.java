package jats.utfpl.stfpl.mycspinstructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jats.utfpl.instruction.AtomValue;
import jats.utfpl.instruction.FunctionInstruction;
import jats.utfpl.instruction.GlobalEntity;
import jats.utfpl.instruction.GlobalValue;
import jats.utfpl.instruction.InsCondAlloc;
import jats.utfpl.instruction.InsCondRelease;
import jats.utfpl.instruction.InsMCAssert;
import jats.utfpl.instruction.InsMCGet;
import jats.utfpl.instruction.InsMCSet;
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
import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.instructions.SIdFactory;
import jats.utfpl.stfpl.mcinstruction.IMCInsVisitor;
import jats.utfpl.stfpl.mcinstruction.IMCInstruction;
import jats.utfpl.stfpl.mcinstruction.IMCValPrim;
import jats.utfpl.stfpl.mcinstruction.MCAtomValue;
import jats.utfpl.stfpl.mcinstruction.MCDecAtomValGroup;
import jats.utfpl.stfpl.mcinstruction.MCDefFun;
import jats.utfpl.stfpl.mcinstruction.MCDefFunGroup;
import jats.utfpl.stfpl.mcinstruction.MCGlobalExtCode;
import jats.utfpl.stfpl.mcinstruction.MCInsAtomRefCreate;
import jats.utfpl.stfpl.mcinstruction.MCInsAtomRefGet;
import jats.utfpl.stfpl.mcinstruction.MCInsAtomRefUpdate;
import jats.utfpl.stfpl.mcinstruction.MCInsCall;
import jats.utfpl.stfpl.mcinstruction.MCInsClosure;
import jats.utfpl.stfpl.mcinstruction.MCInsCond;
import jats.utfpl.stfpl.mcinstruction.MCInsFormEnv;
import jats.utfpl.stfpl.mcinstruction.MCInsGetEleFromEnv;
import jats.utfpl.stfpl.mcinstruction.MCInsMCAssert;
import jats.utfpl.stfpl.mcinstruction.MCInsMCAtomicStart;
import jats.utfpl.stfpl.mcinstruction.MCInsMCGet;
import jats.utfpl.stfpl.mcinstruction.MCInsMCSet;
import jats.utfpl.stfpl.mcinstruction.MCInsMCVLockViewGet;
import jats.utfpl.stfpl.mcinstruction.MCInsMove;
import jats.utfpl.stfpl.mcinstruction.MCInsMutexCreate;
import jats.utfpl.stfpl.mcinstruction.MCInsPatLabDecompose;
import jats.utfpl.stfpl.mcinstruction.MCInsSharedCreateCond;
import jats.utfpl.stfpl.mcinstruction.MCInsThreadCreate;
import jats.utfpl.stfpl.mcinstruction.MCInsTuple;
import jats.utfpl.stfpl.mcinstruction.MCSId;
import jats.utfpl.stfpl.mcinstruction.MCSIdFactory;
import jats.utfpl.stfpl.mcinstruction.ProgramMCIns;
import jats.utfpl.stfpl.staexp.FUNCLOfun;
import jats.utfpl.stfpl.stype.FunType;
import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.VoidType;

public class MyCspInsTransformer {
    
    private List<VariableInfo> m_gvs;
    private List<MyCspGroup> m_main;
    private List<FunctionMyCsp> m_procs;
    private List<MCGlobalExtCode> m_exts;
    
    
    private ProgramMCIns m_prog;
    private MCSIdFactory m_mfac;
    
    public MyCspInsTransformer(ProgramMCIns prog) {
        m_prog = prog;
        
    }
    
    /*
     * The transformation includes the following.
     * 1. Group instructions into block. Prepare for the analysis of each values (def / use).
     * 2. Decide whether a value is used out of its visible scope.
     * 3. Add the concept of stack location.
     * 4. Add return instruction
     */
    public ProgramMyCspIns trans() {
        Map<MCSId, VariableInfo> subMap = new HashMap<MCSId, VariableInfo>();
        
        // ==== handle global variables
        List<VariableInfo> globalVarInfo = new ArrayList<VariableInfo>();
        for (MCDecAtomValGroup ggv: m_prog.m_global_v) {
            for (MCSId mcsid: ggv.m_names) {
                if (false == mcsid.getSId().isFunName()) {
                    VariableInfo vi = VariableInfo.createGlobalValue(mcsid);
                    globalVarInfo.add(vi);

                    subMap.put(mcsid, vi);
                }
            }
        }

        // ==== handle main
        List<ISType> args_type = new ArrayList<ISType>();
        FunType main_type = new FunType(args_type, VoidType.cInstance, FUNCLOfun.cInstance);
        
        SIdFactory sfac = m_mfac.getSIdFac();
        SId main_sid = sfac.createLambdaFunction("main", main_type);
        MCSId main_mcsid = m_mfac.fromSId(main_sid);
        
        List<MyCspGroup> main_body = InsLst2CBlockLst2(m_prog.m_main_inss, subMap, main_mcsid);
        // add the concept of stack
        processBlockLstForStack(0, main_body);

        // == handle other functions
        List<FunctionMyCsp> procLst = new ArrayList<FunctionMyCsp>();
        for (MCDefFunGroup fungrp : m_prog.m_defs) {
            for (MCDefFun fundef: fungrp.m_funs) {
                int pos = 0;
                FunctionMyCsp funcCSP = FunDef2CProcess(fundef, subMap);
                for (MyCspTempID para: funcCSP.m_paras) {
                    pos = para.processStackPrelogue(pos);
                }
                
                // add the concept of stack
                processBlockLstForStack(pos, funcCSP.m_body);
                procLst.add(funcCSP);
            }
        }

        m_gvs = globalVarInfo;
        m_main = main_body;
        m_procs =procLst;
        m_exts = m_prog.m_exts;
        
        ProgramMyCspIns nprog = new ProgramMyCspIns(m_gvs, m_main, m_procs, m_exts);
        return nprog;
    }
    
    private static class InsLst2CBlockLstConverter implements IMCInsVisitor {
        private List<MyCspGroup> m_cblkLst;
        private GrpEvent m_cbEvt;
        private Map<MCSId, VariableInfo> m_subMap;
        private MCSId m_funLab;
        
        public InsLst2CBlockLstConverter(Map<MCSId, VariableInfo> subMap, MCSId funLab) {
            m_cblkLst = new ArrayList<MyCspGroup>();
            m_cbEvt = new GrpEvent();
            m_subMap = subMap;
            m_funLab = funLab;
        }
        
        public List<MyCspGroup> getCBlockLst() {
            return m_cblkLst;
        }
        
        public void finalize() {
            if (0 != m_cbEvt.size()) {
                m_cblkLst.add(m_cbEvt);
            }
        }

        @Override
        public Object visit(MCInsCond ins) {
            if (ins.hasSideEffect()) {
                GrpCond ccond = new GrpCond();
                IMyCspTemp ctCond = ValPrim2CTemp(ins.m_cond, m_subMap, m_funLab, ccond);

                Map<MCSId, VariableInfo> subMapTrue = 
                        new HashMap<MCSId, VariableInfo>(m_subMap);
                Map<MCSId, VariableInfo> subMapFalse = 
                        new HashMap<MCSId, VariableInfo>(m_subMap);

                List<MyCspGroup> btrue = InsLst2CBlockLst2(ins.m_btrue, subMapTrue, m_funLab);
                List<MyCspGroup> bfalse = InsLst2CBlockLst2(ins.m_bfalse, subMapFalse, m_funLab);

                // connect all the links
                ccond.reset(ctCond, btrue, bfalse);

                if (0 != m_cbEvt.size()) {
                    m_cblkLst.add(m_cbEvt);
                    m_cbEvt = new GrpEvent();
                }
                m_cblkLst.add(ccond);
                return null;
                
            } else {
                IMyCspTemp ctCond = ValPrim2CTemp(ins.m_cond, m_subMap, m_funLab, m_cbEvt);

                // create a CTempID as definition
                MyCspTempID ctHolder = null;
                if (!ins.m_holder.getSId().isRetHolder()) {
                	ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                }
                
                Map<MCSId, VariableInfo> subMap2 = 
                        new HashMap<MCSId, VariableInfo>(m_subMap);
                
                // This is a hack. Though the InsCond would be turned into a CICond,
                // we need to update its content (TID -> CTempID).
                // save the environment
                Map<MCSId, VariableInfo> subMapBackup = m_subMap;
                m_subMap = subMap2;
                List<MyCspGroup> blkLstBackup = m_cblkLst;
                List<MyCspInstruction> insLstBackup = m_cbEvt.m_inslst;
                
                m_cblkLst = new ArrayList<MyCspGroup>();
                m_cbEvt.m_inslst = new ArrayList<MyCspInstruction>();

                for (IMCInstruction bins: ins.m_btrue) {
                    bins.accept(this);
                }
                List<MyCspInstruction> trueBranch = m_cbEvt.m_inslst;
                
                // If the holder of the InsCond is of ret, then holder of the last instruction of
                // the branch would be of ret. This will trigger m_cbEvt to
                // included in m_cblkLst.
                if (trueBranch.size() < 1) {
                	GrpEvent cbEvt = (GrpEvent)m_cblkLst.get(0);
                	trueBranch = cbEvt.m_inslst;
                }
                
                m_cblkLst = new ArrayList<MyCspGroup>();
                m_cbEvt.m_inslst = new ArrayList<MyCspInstruction>();

                for (IMCInstruction bins: ins.m_bfalse) {
                    bins.accept(this);
                }
                List<MyCspInstruction> falseBranch = m_cbEvt.m_inslst;
                
                // If the holder of the InsCond is of ret, then holder of the last instruction of
                // the branch would be of ret. This will trigger m_cbEvt to
                // included in m_cblkLst.
                if (falseBranch.size() < 1) {
                	GrpEvent cbEvt = (GrpEvent)m_cblkLst.get(0);
                	falseBranch = cbEvt.m_inslst;
                }
                
                // recover the environment
                m_subMap = subMapBackup;
                m_cbEvt.m_inslst = insLstBackup;
                m_cblkLst = blkLstBackup;
                
                CICond nIns = new CICond(ctHolder/* may be null*/, ctCond, trueBranch, falseBranch, m_cbEvt);
                if (!ins.m_holder.getSId().isRetHolder() && ctHolder.isDefinition()) {
//                    System.out.println("dddddddddddddddddd holder is " + ins.m_holder + "in " + m_funLab);
                    CIVarDef defIns = new CIVarDef(ctHolder, m_cbEvt);
                    m_cbEvt.add(defIns);  // add an extra instruction to hold the value caused by the "if"
                }
                
                m_cbEvt.add(nIns);

                if (ins.m_holder.getSId().isRetHolder()) {
                    m_cblkLst.add(m_cbEvt);
                    m_cbEvt = new GrpEvent();
                }
                return null;
            }
        }

        @Override
        public Object visit(MCInsCall ins) {
            if (!ins.m_fun.getSId().isFunName()) {
                throw new Error("Currently, function is not first order value.");
            }
            if (ins.hasSideEffect()) {
                List<IMyCspTemp> nLst = ValPrimLst2CTempLst(ins.m_args, m_subMap, m_funLab, m_cbEvt);
                CIProcCallPrelogue cCallPre = new CIProcCallPrelogue(m_cbEvt, nLst, ins.isTailCall());
                m_cbEvt.add(cCallPre);
                m_cblkLst.add(m_cbEvt);
                m_cbEvt = new GrpEvent();
                
                GrpProc cprocess = new GrpProc(ins.m_fun, ins.isTailCall());
                m_cblkLst.add(cprocess);
                
                // No epilogue at all.
                if (!ins.m_holder.getSId().isRetHolder()) {
                    MyCspTempID ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                    CIProcCallEpilog cCallEpi = new CIProcCallEpilog(m_cbEvt, ins.m_fun, ctHolder);
                    m_cbEvt.add(cCallEpi);
                } 

                return null;

            } else {
                List<IMyCspTemp> nLst = ValPrimLst2CTempLst(ins.m_args, m_subMap, m_funLab, m_cbEvt);
                MyCspTempID ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIFunCall nIns = new CIFunCall(ins.m_fun, nLst, ctHolder, ins.isTailCall(), m_cbEvt);

                m_cbEvt.add(nIns);

                // Add return ins
                if (ins.m_holder.getSId().isRetHolder()) {
                    MyCspTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                    CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                    m_cbEvt.add(retIns);
                    m_cblkLst.add(m_cbEvt);
                    m_cbEvt = new GrpEvent();
                }
                return null;
            }
        }

//        @Override
//        public Object visit(InsStoreArray ins) {
//
//            IMyCspTemp localValue = ValPrim2CTemp(ins.m_localValue, m_subMap, m_funLab, m_cbEvt);
//            MyCspTempID globalVar = TID2CTempID(ins.m_globalVar, m_subMap, m_funLab, m_cbEvt);
//            IMyCspTemp localIndex = ValPrim2CTemp(ins.m_localIndex, m_subMap, m_funLab, m_cbEvt);
//            
//            CIStoreArray nIns = new CIStoreArray(localValue, globalVar, localIndex, m_cbEvt);
//            m_cbEvt.add(nIns);
//            m_cblkLst.add(m_cbEvt);
//            m_cbEvt = new GrpEvent();
//            
//            return null;            
//        }

        @Override
        public Object visit(MCInsAtomRefUpdate ins) {

            IMyCspTemp localSrc = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID globalDest = TID2CTempID(ins.m_ref, m_subMap, m_funLab, m_cbEvt);
            
            CIAtomRefUpdate nIns = new CIAtomRefUpdate(localSrc, globalDest, m_cbEvt);
            m_cbEvt.add(nIns);
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new GrpEvent();
            
            return null; 
        }
        

        @Override
        public Object visit(MCInsAtomRefGet ins) {
            
            MyCspTempID globalVar = TID2CTempID(ins.m_ref, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID localHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            
            CIAtomRefGet nIns = new CIAtomRefGet(globalVar, localHolder, m_cbEvt);
            m_cbEvt.add(nIns);
            
            // Add return ins
            if (ins.m_holder.isRet()) {
                MyCspTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
            }
            
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new GrpEvent();
            
            return null;
        }

//        @Override
//        public Object visit(InsRet ins) {
//            IMyCspTemp v = ValPrim2CTemp(ins.m_v, m_subMap, m_funLab, m_cbEvt);
//                    
//            CIReturn nIns = new CIReturn(v, m_cbEvt);
//            m_cbEvt.add(nIns);
//            m_cblkLst.add(m_cbEvt);
//            m_cbEvt = new GrpEvent();
//
//            return null;
//        }

//        @Override
//        public Object visit(InsLoadArray ins) {
//
//            MyCspTempID localHolder = TID2CTempID(ins.m_localHolder, m_subMap, m_funLab, m_cbEvt);
//            MyCspTempID globalVar = TID2CTempID(ins.m_globalVar, m_subMap, m_funLab, m_cbEvt);
//            IMyCspTemp localIndex = ValPrim2CTemp(ins.m_localIndex, m_subMap, m_funLab, m_cbEvt);
//            
//            CILoadArray nIns = new CILoadArray(globalVar, localIndex, localHolder, m_cbEvt);
//            m_cbEvt.add(nIns);
//            
//            // Add return ins
//            if (ins.m_localHolder.isRet()) {
//                MyCspTempID retCTempID = TID2CTempID(ins.m_localHolder, m_subMap, m_funLab, m_cbEvt);
//                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
//                m_cbEvt.add(retIns);
//            }
//            
//            m_cblkLst.add(m_cbEvt);
//            m_cbEvt = new GrpEvent();
//            
//            return null;    
//            
//        }


        @Override
        public Object visit(MCInsMove ins) {
            if (ins.m_holder.isRet()) {
                MyCspTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
                m_cblkLst.add(m_cbEvt);
                m_cbEvt = new GrpEvent();
            } else {
                IMyCspTemp v = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
                MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIMove nIns = new CIMove(v, holder, m_cbEvt);
                m_cbEvt.add(nIns);
            }


            return null;
        }

		@Override
        public Object visit(MCInsThreadCreate ins) {
			GrpThreadCreate nBlk = new GrpThreadCreate(ins.m_funlab);
			
	        IMyCspTemp tid = ValPrim2CTemp(ins.m_tid, m_subMap, m_funLab, nBlk);
	        IMyCspTemp args = ValPrim2CTemp(ins.m_arg, m_subMap, m_funLab, nBlk);
	        
	        nBlk.setContent(tid, args);
	        
            if (0 != m_cbEvt.size()) {
                m_cblkLst.add(m_cbEvt);
                m_cbEvt = new GrpEvent();
            }
            m_cblkLst.add(nBlk);
            return null;
        }

        @Override
        public Object visit(MCInsMutexCreate ins) {
            MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            
            CIMutexAlloc nIns = new CIMutexAlloc(holder, m_cbEvt);
            m_cbEvt.add(nIns);
            
            // Add return ins
            if (ins.m_holder.isRet()) {
                MyCspTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
            }
            
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new GrpEvent();
            
            return null;
        }
        
//		@Override
//        public Object visit(InsMutexRelease ins) {
//			IMyCspTemp mutex = ValPrim2CTemp(ins.m_mutex, m_subMap, m_funLab, m_cbEvt);
//			CIMutexRelease nIns = new CIMutexRelease(m_cbEvt, mutex);
//            m_cbEvt.add(nIns);
//            m_cblkLst.add(m_cbEvt);
//            m_cbEvt = new GrpEvent();
//            return null;
//        }
		

        @Override
        public Object visit(MCInsSharedCreateCond ins) {
            MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            
            CICondAlloc nIns = new CICondAlloc(holder, m_cbEvt);
            m_cbEvt.add(nIns);
            
            // Add return ins
            if (ins.m_holder.isRet()) {
                MyCspTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
            }
            
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new GrpEvent();
            
            return null;
        }
        
//		@Override
//        public Object visit(InsCondRelease ins) {
//			IMyCspTemp cond = ValPrim2CTemp(ins.m_cond, m_subMap, m_funLab, m_cbEvt);
//			CICondRelease nIns = new CICondRelease(m_cbEvt, cond);
//            m_cbEvt.add(nIns);
//            m_cblkLst.add(m_cbEvt);
//            m_cbEvt = new GrpEvent();
//            return null;
//        }

        @Override
        public Object visit(MCInsMCAssert ins) {
            IMyCspTemp localSrc = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
            
            CIMCAssert nIns = new CIMCAssert(localSrc, m_cbEvt);
            m_cbEvt.add(nIns);
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new GrpEvent();
            
            return null; 
        }

        @Override
        public Object visit(MCInsMCGet ins) {
            MyCspTempID globalVar = TID2CTempID(ins.m_src, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID localHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            
            CIMCGet nIns = new CIMCGet(globalVar, localHolder, m_cbEvt);
            m_cbEvt.add(nIns);
            
            // Add return ins
            if (ins.m_holder.isRet()) {
                throw new Error("This should not happen.");
            }
            
            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new GrpEvent();
            
            return null; 
        }

        @Override
        public Object visit(MCInsMCSet ins) {
            IMyCspTemp localSrc = ValPrim2CTemp(ins.m_src, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID globalDest = TID2CTempID(ins.m_dst, m_subMap, m_funLab, m_cbEvt);
            
            CIMCSet nIns = new CIMCSet(localSrc, globalDest, m_cbEvt);
            m_cbEvt.add(nIns);

            m_cblkLst.add(m_cbEvt);
            m_cbEvt = new GrpEvent();
            
            return null; 
        }

        @Override
        public Object visit(MCInsTuple ins) {
            List<IMyCspTemp> nLst = ValPrimLst2CTempLst(ins.m_elements, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            CIFormTuple nIns = new CIFormTuple(nLst, ctHolder, m_cbEvt);

            m_cbEvt.add(nIns);

            // Add return ins
            if (ins.m_holder.getSId().isRetHolder()) {
                MyCspTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
                m_cblkLst.add(m_cbEvt);
                m_cbEvt = new GrpEvent();
            }
            return null;
        }

        @Override
        public Object visit(MCInsPatLabDecompose ins) {
            IMyCspTemp vp = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            CIPatLabDecompose nIns = new CIPatLabDecompose(holder, vp, ins.m_lab, m_cbEvt);
            
            m_cbEvt.add(nIns);
            
            // Add return ins
            if (ins.m_holder.getSId().isRetHolder()) {
                MyCspTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
                m_cblkLst.add(m_cbEvt);
                m_cbEvt = new GrpEvent();
            }
            return null;
        }

        @Override
        public Object visit(MCInsFormEnv ins) {
            Set<MyCspTempID> nLst = ValPrimSet2CTempSet(ins.m_env, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            CIFormEnv nIns = new CIFormEnv(nLst, ctHolder, m_cbEvt);

            m_cbEvt.add(nIns);

            // Add return ins
            if (ins.m_holder.getSId().isRetHolder()) {
                MyCspTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
                m_cblkLst.add(m_cbEvt);
                m_cbEvt = new GrpEvent();
            }
            return null;
        }

        @Override
        public Object visit(MCInsGetEleFromEnv ins) {
        	MyCspTempID env    = TID2CTempID(ins.m_env, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            CIGetEleFromEnv nIns = new CIGetEleFromEnv(holder, env, ins.m_tag, m_cbEvt);
            
            m_cbEvt.add(nIns);
            
            // Add return ins
            if (ins.m_holder.getSId().isRetHolder()) {
                MyCspTempID retCTempID = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
                m_cbEvt.add(retIns);
                m_cblkLst.add(m_cbEvt);
                m_cbEvt = new GrpEvent();
            }
            return null;
        }

        @Override
        public Object visit(MCInsClosure ins) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Object visit(MCInsAtomRefCreate ins) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Object visit(MCInsMCAtomicStart ins) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public Object visit(MCInsMCVLockViewGet ins) {
            // TODO Auto-generated method stub
            return null;
        }
    }

    static private List<MyCspGroup> InsLst2CBlockLst2(List<IMCInstruction> insLst, Map<MCSId, VariableInfo> subMap, MCSId funLab)
    {
        InsLst2CBlockLstConverter cvt = new InsLst2CBlockLstConverter(subMap, funLab);
        for (IMCInstruction ins: insLst) {
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
    static private void processBlockLstForStack(int offset, List<MyCspGroup> blkLst) {
        for (MyCspGroup blk: blkLst) {
            offset = blk.process(offset);            
        }
    }
    
    /*
     * Get an object of CTemp from an object of ValPrim.
     * "map" may be updated.
     * "grp" is the group which holds the object of CTemp.
     */
    static private IMyCspTemp ValPrim2CTemp(IMCValPrim vp, Map<MCSId, VariableInfo> map, MCSId funLab, MyCspGroup grp) {
        if (vp instanceof AtomValue) {
            return new MyCspTempVal((MCAtomValue) vp);
        } else if (vp instanceof MCSId) {
            MCSId tid = (MCSId)vp;
            return TID2CTempID(tid, map, funLab, grp);
        } else {
            throw new Error("shall not happen");
        }
    }
    
    /*
     * Create a new list of CTemp from a list of ValPrim.
     */
    static private List<IMyCspTemp> ValPrimLst2CTempLst(List<IMCValPrim> vpLst, Map<MCSId, VariableInfo> map, MCSId funLab, MyCspGroup grp) {
    	List<IMyCspTemp> nLst = new ArrayList<IMyCspTemp>();
    	for (IMCValPrim vp: vpLst) {
    		IMyCspTemp newVp = ValPrim2CTemp(vp, map, funLab, grp);
    		nLst.add(newVp);
    	}
    	return nLst;
    }
    
    static private Set<MyCspTempID> ValPrimSet2CTempSet(Set<MCSId> vpSet, Map<MCSId, VariableInfo> map, MCSId funLab, MyCspGroup grp) {
    	Set<MyCspTempID> nSet = new HashSet<MyCspTempID>();
    	for (MCSId vp: vpSet) {
    		MyCspTempID newVp = TID2CTempID(vp, map, funLab, grp);
    		nSet.add(newVp);
    	}
    	return nSet;
    }
    
    /*
     * If "VariableInfo" is not in the map, then it's a definition. 
     * Otherwise, it's a usage.
     */
    static private MyCspTempID TID2CTempID(MCSId tid, Map<MCSId, VariableInfo> map, MCSId funLab, MyCspGroup grp) {
        EntityLocation loc = EntityLocation.create(funLab, grp);
        MyCspTempID ret = null;
//        if (tid == TID.ANONY) {
//        	VariableInfo vi = VariableInfo.create(tid, loc);
//        	// Do not put into map.
//            ret = MyCspTempID.createAsDef(vi, loc);
//            return ret;
//        }
        
        VariableInfo vi = map.get(tid);
        if (null == vi) {
//            if (tid.isGlobalVariable()) {
//                throw new Error("Wong usage. Only for global value, parameter, and local variable");
//            }
            vi = VariableInfo.create(tid, loc);
            map.put(tid, vi);  // This is important.
            ret = MyCspTempID.createAsDef(vi, loc);
        } else {
            vi.addUsage(loc);
            ret = MyCspTempID.createAsUsage(vi, loc);
        }
        return ret;
    }
        
    static private FunctionMyCsp FunDef2CProcess(
            MCDefFun funDef
            , Map<MCSId, VariableInfo> subMap) {
        List<MyCspTempID> paras = new ArrayList<MyCspTempID>();
        for (MCSId para: funDef.m_paras) {
            MyCspTempID cPara = TID2CTempID(para, subMap, funDef.m_name, null /* CBlock is null */);
            paras.add(cPara);
        }
        for (MCSId para: funDef.m_paras) {
            MyCspTempID cPara = TID2CTempID(para, subMap, funDef.m_name, null /* CBlock is null */);
            paras.add(cPara);
        }
        
        List<MyCspGroup> body = InsLst2CBlockLst2(funDef.m_inss, subMap, funDef.m_name);
        FunctionMyCsp cProc = new FunctionMyCsp(funDef.m_name, paras, body);
        return cProc;
    }
}




