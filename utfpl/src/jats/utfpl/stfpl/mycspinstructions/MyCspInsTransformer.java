package jats.utfpl.stfpl.mycspinstructions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import jats.utfpl.stfpl.mcinstruction.MCInsArrayRefCreate;
import jats.utfpl.stfpl.mcinstruction.MCInsArrayRefGet;
import jats.utfpl.stfpl.mcinstruction.MCInsArrayRefUpdate;
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
import jats.utfpl.stfpl.stype.AuxSType;
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
    
    public MyCspInsTransformer(ProgramMCIns prog, MCSIdFactory mfac) {
        m_prog = prog;
        m_mfac = mfac;
        
    }
    
    /*
     * The transformation includes the following.
     * 1. Group instructions into block. Prepare for the analysis of each values (def / use).
     * 2. Decide whether a value is used out of its visible scope.
     * 3. Add the concept of stack location.
     * 4. Add return instruction
     */
    public ProgramMyCspIns transform() {
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
                    pos = para.processFunPara(pos);
                }
   
//                if (null != funcCSP.m_envname) {
//                    pos = funcCSP.m_envname.processStackPrelogue(pos);
//                }
                
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
            
//            MyCspTempID envname = TID2CTempID(ins.m_env, m_subMap, m_funLab, m_cbEvt);
            List<IMyCspTemp> nLst = ValPrimLst2CTempLst(ins.m_args, m_subMap, m_funLab, m_cbEvt);
            
            if (ins.hasSideEffect()) {
                
                CIProcCallPrelogue cCallPre = new CIProcCallPrelogue(m_cbEvt, nLst, ins.isTailCall());
                m_cbEvt.add(cCallPre);
                m_cblkLst.add(m_cbEvt);
                m_cbEvt = new GrpEvent();
                
                GrpProc cprocess = new GrpProc(ins.m_fun, ins.isTailCall());
                m_cblkLst.add(cprocess);
                
                // No epilogue at all if is tail call.
                if (!ins.m_holder.getSId().isRetHolder()) {
                    MyCspTempID ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                    CIProcCallEpilog cCallEpi = new CIProcCallEpilog(m_cbEvt, ins.m_fun, ctHolder);
                    m_cbEvt.add(cCallEpi);
                } 

                return null;

            } else {
                
//            	System.out.println("========== name is " + ins.m_fun.toStringMCIns());
                MyCspTempID ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
                CIFunCall nIns = new CIFunCall(ins.m_fun, nLst, ctHolder, ins.isTailCall(), m_cbEvt);

                handleReturnForNoEffect(nIns, ins.m_holder);
                
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
	        // No return for void
	        if (false == AuxSType.isVoid(ins.m_holder.getType())) {
	            if (ins.m_holder.isRet()) {
	            	IMyCspTemp retCTempID = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
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
        public Object visit(MCInsAtomRefCreate ins) {
            MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            IMyCspTemp vp = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
            
            CIAtomRefCreate nIns = new CIAtomRefCreate(holder, vp, m_cbEvt);
            
            handleReturnForWithEffect(nIns, ins.m_holder);
            
            return null;
        }


        @Override
        public Object visit(MCInsAtomRefUpdate ins) {

            IMyCspTemp localSrc = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID globalDest = TID2CTempID(ins.m_ref, m_subMap, m_funLab, m_cbEvt);
            
            CIAtomRefUpdate nIns = new CIAtomRefUpdate(localSrc, globalDest, m_cbEvt);

            handleNoReturnForWithEffect(nIns);
            
            return null; 
        }

        @Override
        public Object visit(MCInsAtomRefGet ins) {
            
            MyCspTempID globalVar = TID2CTempID(ins.m_ref, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID localHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            
            CIAtomRefGet nIns = new CIAtomRefGet(globalVar, localHolder, m_cbEvt);

            handleReturnForWithEffect(nIns, ins.m_holder);
            
            return null;
        }

        @Override
        public Object visit(MCInsMutexCreate ins) {
            MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            
            CIMutexCreate nIns = new CIMutexCreate(holder, m_cbEvt);
            
            handleReturnForWithEffect(nIns, ins.m_holder);
            
            return null;
        }

        @Override
        public Object visit(MCInsSharedCreateCond ins) {
            MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            
            CISharedCreateCond nIns = new CISharedCreateCond(holder, m_cbEvt);
            
            handleReturnForWithEffect(nIns, ins.m_holder);
            
            return null;
        }

        @Override
        public Object visit(MCInsMCAssert ins) {
            IMyCspTemp localSrc = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
            
            CIMCAssert nIns = new CIMCAssert(localSrc, m_cbEvt);
            m_cbEvt.add(nIns);
            
            // Add return ins
            if (ins.m_isret) {
                throw new Error("This should not happen.");
            }
            
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

            handleNoReturnForWithEffect(nIns);
            
            return null; 
        }

        @Override
        public Object visit(MCInsTuple ins) {
            List<IMyCspTemp> nLst = ValPrimLst2CTempLst(ins.m_elements, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            CIFormTuple nIns = new CIFormTuple(nLst, ctHolder, m_cbEvt);

            handleReturnForNoEffect(nIns, ins.m_holder);

            return null;
        }

        @Override
        public Object visit(MCInsPatLabDecompose ins) {
            IMyCspTemp vp = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            CIPatLabDecompose nIns = new CIPatLabDecompose(holder, vp, ins.m_lab, ins.m_index, m_cbEvt);

            handleReturnForNoEffect(nIns, ins.m_holder);

            return null;
        }

        @Override
        public Object visit(MCInsFormEnv ins) {
        	List<MyCspTempID> nLst = MCSIdList2CTempIdList(ins.m_env, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID ctHolder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            CIFormEnv nIns = new CIFormEnv(nLst, ctHolder, m_cbEvt);

            handleReturnForNoEffect(nIns, ins.m_holder);
            
            return null;
        }

        @Override
        public Object visit(MCInsGetEleFromEnv ins) {
        	MyCspTempID env    = TID2CTempID(ins.m_env, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            CIGetEleFromEnv nIns = new CIGetEleFromEnv(holder, env, ins.m_tag, ins.m_index, m_cbEvt);

            handleReturnForNoEffect(nIns, ins.m_holder);
            
            return null;
        }

        @Override
        public Object visit(MCInsClosure ins) {
        	MyCspTempID fun_name = TID2CTempID(ins.m_fun, m_subMap, m_funLab, m_cbEvt);
        	MyCspTempID env_name = TID2CTempID(ins.m_env, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID holder   = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            CIFormClosure nIns = new CIFormClosure(holder, fun_name, env_name, m_cbEvt);

            handleReturnForNoEffect(nIns, ins.m_holder);
            
            return null;
        }


        @Override
        public MCInsMCAtomicStart visit(MCInsMCAtomicStart ins) {
            GrpMCAtomicStart grp = new GrpMCAtomicStart();
            if (0 != m_cbEvt.size()) {
                m_cblkLst.add(m_cbEvt);
                m_cbEvt = new GrpEvent();
            }
            m_cblkLst.add(grp);
            return null;
        }

        @Override
        public Object visit(MCInsMCVLockViewGet ins) {
            List<IMyCspTemp> nLst = ValPrimLst2CTempLst(ins.m_args, m_subMap, m_funLab, m_cbEvt);
            CIMCVLockViewGet nIns = new CIMCVLockViewGet(nLst, m_cbEvt);

            handleNoReturnForWithEffect(nIns);

            return null;
        }
        
        /*
         *  handleXXReturnForXXEffect
         *  1. handle  No  ReturnFor  With  Effect
         *  2. handle      ReturnFor  With  Effect
         *  3. handle      ReturnFor  No    Effect
         */ 
        // Add instruction and start a new group.
        private void handleNoReturnForWithEffect(MyCspInstruction ins) {
	        m_cbEvt.add(ins);
	        m_cblkLst.add(m_cbEvt);
	        m_cbEvt = new GrpEvent();
        }
        
        private void handleReturnForWithEffect(MyCspInstruction ins, MCSId holder) {
        	m_cbEvt.add(ins);
        	
	        if (holder.isRet()) {
	            MyCspTempID retCTempID = TID2CTempID(holder, m_subMap, m_funLab, m_cbEvt);
		        // No return for void
		        if (false == AuxSType.isVoid(holder.getType())) {
			        CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
			        m_cbEvt.add(retIns);
		        }
	        }
	        
	        // No matter whether it is return, we start a new group.
	        m_cblkLst.add(m_cbEvt);
	        m_cbEvt = new GrpEvent();
        }
        
        /*
         * Handle the return for instruction without effect.
         */
        private void handleReturnForNoEffect(MyCspInstruction ins, MCSId holder) {
        	m_cbEvt.add(ins);
        	
            // Add return ins
            if (holder.getSId().isRetHolder()) {
            	addReturnIns(holder);
            }
        }
        
        private void addReturnIns(MCSId holder) {
	        MyCspTempID retCTempID = TID2CTempID(holder, m_subMap, m_funLab, m_cbEvt);
	        // No return for void
	        if (false == AuxSType.isVoid(holder.getType())) {
		        CIReturn retIns = new CIReturn(retCTempID, m_cbEvt);
		        m_cbEvt.add(retIns);
	        }
	        m_cblkLst.add(m_cbEvt);
	        m_cbEvt = new GrpEvent();
        }

		@Override
		public Object visit(MCInsArrayRefCreate ins) {
            MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            IMyCspTemp len = ValPrim2CTemp(ins.m_len, m_subMap, m_funLab, m_cbEvt);
            IMyCspTemp vp = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
            
            CIArrayRefCreate nIns = new CIArrayRefCreate(holder, len, vp, m_cbEvt);
            
            handleReturnForWithEffect(nIns, ins.m_holder);
            
            return null;
		}

		@Override
		public Object visit(MCInsArrayRefUpdate ins) {
            IMyCspTemp v = ValPrim2CTemp(ins.m_vp, m_subMap, m_funLab, m_cbEvt);
            IMyCspTemp pos = ValPrim2CTemp(ins.m_pos, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID ref = TID2CTempID(ins.m_ref, m_subMap, m_funLab, m_cbEvt);
            
            
            CIArrayRefUpdate nIns = new CIArrayRefUpdate(ref, pos, v, m_cbEvt);

            handleNoReturnForWithEffect(nIns);
            
            return null; 
		}

		@Override
		public Object visit(MCInsArrayRefGet ins) {
            MyCspTempID ref = TID2CTempID(ins.m_ref, m_subMap, m_funLab, m_cbEvt);
            IMyCspTemp pos = ValPrim2CTemp(ins.m_pos, m_subMap, m_funLab, m_cbEvt);
            MyCspTempID holder = TID2CTempID(ins.m_holder, m_subMap, m_funLab, m_cbEvt);
            
            CIArrayRefGet nIns = new CIArrayRefGet(ref, pos, holder, m_cbEvt);

            handleReturnForWithEffect(nIns, ins.m_holder);
            
            return null;
		}
        
        /* ********** ********** *********** */
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
        if (vp instanceof MCAtomValue) {
            return new MyCspTempVal((MCAtomValue) vp);
        } else if (vp instanceof MCSId) {
            MCSId tid = (MCSId)vp;
            return TID2CTempID(tid, map, funLab, grp);
        } else {
            throw new Error("shall not happen vp is " + vp);
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
    
    static private List<MyCspTempID> MCSIdList2CTempIdList(List<MCSId> vpList, Map<MCSId, VariableInfo> map, MCSId funLab, MyCspGroup grp) {
    	List<MyCspTempID> nSet = new ArrayList<MyCspTempID>();
    	for (MCSId vp: vpList) {
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
        
//        MyCspTempID envname = null;
        for (MCSId para: funDef.m_paras) {
            MyCspTempID cPara = TID2CTempID(para, subMap, funDef.m_name, null /* CBlock is null */);
            paras.add(cPara);
        }
        
//        if (null != funDef.m_env_name) {
//        	envname = TID2CTempID(funDef.m_env_name, subMap, funDef.m_name, null);
//        }
        
        List<MyCspGroup> body = InsLst2CBlockLst2(funDef.m_inss, subMap, funDef.m_name);
        
        
        FunctionMyCsp cProc = new FunctionMyCsp(funDef.m_name, paras, body);
        return cProc;
    }
}




