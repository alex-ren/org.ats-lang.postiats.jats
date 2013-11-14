package jats.utfpl.patcsps;

import java.util.List;


public class PModel implements PNode {
    public List<PGDec> m_gvLst;
    public PProc m_mainProcBody;
    public List<PGDecProc> m_procLst;  // all the processes in the program
    
//    public List<PGDecProc> m_threadLst;  // wrapper for those processes (on the outmost level) which only take one argument
//    public PProc m_SchedulerBody;
    
//    private List<PInclude> m_inclLst;
//    private List<PGDec> m_sysGVarLst;

    public PModel(List<PGDec> gvLst, PProc mainProcBody, List<PGDecProc> procLst) {
        m_gvLst = gvLst;
        m_mainProcBody = mainProcBody;
        m_procLst = procLst;
        
//        m_threadLst = new ArrayList<PGDecProc>();
//        m_SchedulerBody = null;
        
//        m_inclLst = new ArrayList<PInclude>();
//        m_sysGVarLst = new ArrayList<PGDec>();
    }
//    
//    public void complete() {
//        // Put into patscsp.stg ===================
////    	m_inclLst.add(new PInclude("PStack"));
//    	
////    	m_sysGVarLst.add(PGDecVar.createInit(Aux.cSysTid, PExpAtom.createFromInt(0)));
////    	m_sysGVarLst.add(new PGDecChan(Aux.cSysSch, 0));
////    	m_sysGVarLst.add(new PGDecChan(Aux.cSysSchStart, 0));
//        // ========================================
//
//    	this.threadize();
//
//    	// todo
////    	// assembly: function address for createThread
////    	Map<PGDecProc, PAddress> threads = constructThread(threadFuncMap);
//
//    }
//    
//    // 1. Add tid to m_procLst as parameters and arguments.
//    // 2. Generate the definition of processes for all the potential threads.
//    // 3. Return pair of function and address
//    private Map<TID, Address> threadize() {
//        
//        // =================================
//        // Preparation for Scheduler
//        //      Scheduler = SysChSch?fn.arg ->
//        //      init{SysTid++; var tid = SysTid; GStack.allocateStack(tid);} ->
//        
//        // <scheduler_body> goes as follows
//        //      ifa (fn == 0) {
//        //        main1_s (true, SysTid, arg) ||| SchedulerW (SysTid)
//        //      } else ifa (fn == 1) {
//        //        fact_15_s (true, SysTid, arg) ||| SchedulerW (SysTid)
//        //      }
//        //      ;
//        PProc scheduler = null;
//        PExpID fn = new PExpID(TID.createPara("fn", true));
//        PExpID arg = new PExpID(TID.createPara("arg", true));
//        
//        List<PExp> argLst = new ArrayList<PExp>();
//        
//        // SchedulerW is defined in patcsps.stg, not here. => SchedulerW (tid) = SysChSchStart?tid -> Scheduler;
//        // Building arguments for SchedulerW
//        argLst.add(Aux.cSysTidExp);
//        // => SchedulerW (SysTid)
//        PProcCall SchedulerW = new PProcCall(Aux.cSchedulerWTid, argLst, true);
//       
//        // =================================
//        // arguments for calling ..._s function
//        // (true, SysTid, arg)
//        argLst = new ArrayList<PExp>();
//        argLst.add(Aux.cFalse);  // ..._s function needs a stack.
//        argLst.add(Aux.cSysTidExp);
//        argLst.add(arg);
//
//        // =================================
//        
//        Map<TID, Address> m = new HashMap<TID, Address>();
//        TIDAdder processor = new TIDAdder();
//        TailProcCallProcessor tailCallProcessor = new TailProcCallProcessor();
//        
//        m_mainProcBody.accept(processor);
//        m_mainProcBody.accept(tailCallProcessor);
//        
//        // add tid, tailcall
//        // build the scheduler
//        for (PGDecProc proc: m_procLst) {
//            proc.accept(processor);
//            proc.accept(tailCallProcessor);
//            // These processes can be used as functions for creating threads.
//            if (0 == proc.m_level && proc.m_paraLst.size() == 3) {  // first is "isTailCall", second is "tid", the third is any
//                List<TID> threadParaLst = new ArrayList<TID>();
//                List<PExp> threadArgLst = new ArrayList<PExp>();
//                
//                // isTailCall
//                threadParaLst.add(proc.m_paraLst.get(0));
//                // tid
//                threadParaLst.add(proc.m_paraLst.get(1));
//                // Keep the uniqueness of TID for non-system names
//                TID newParaTid = TID.createPara(proc.m_paraLst.get(2).getID(), false);
//                threadParaLst.add(newParaTid);
//                
//                for (TID para: threadParaLst) {
//                    threadArgLst.add(new PExpID(para));
//                }
//
//                // build =>
//                // SysChSchStart!tid -> producer_22(isTailCall, tid, x_27)
//                PProcChannel threadBody = new PProcChannel(Aux.cThreadHeader, new PProcCall(proc.m_name, threadArgLst, true));
//                TID threadName = TID.createUserFun(
//                        proc.m_name.getID() + "_s", 
//                        new PATTypeFunc(PATTypeSingleton.cVoidType, true));
//                
//                // build =>
//                // producer_s(isTailCall, tid, x_27) = SysChSchStart!tid -> producer_22(isTailCall, tid, x_27)
//                PGDecProc threadDef = new PGDecProc(threadName, threadParaLst, new ArrayList<TID>() /*no escaped parameter*/, threadBody, 0);
//
//                Aux.Address addr = Aux.Address.createPointer();
//                proc.m_name.updateAddr(addr);
//                m.put(proc.m_name, addr);
//                m_threadLst.add(threadDef);
//
//                // e.g. fn == 0
//                PExpID funPtr = new PExpID(proc.m_name);
//                PExpOpr condExp = new PExpOpr(PExpOpr.Type.eq, fn, funPtr);
//                // build =>
//                // producer_s(true, SysTid, arg) ||| SchedulerW (SysTid)
//                PProc ifProc = new PProcCall(threadName, argLst, false);
//                ifProc = new PProcParallel(ifProc, SchedulerW);
//                
//                // build the process for scheduler
//                scheduler = new PProcBranch(condExp, ifProc, scheduler, PProcBranch.Type.ifa);
//            }
//        }
//        if (null == scheduler) {
//            m_SchedulerBody = PProcAtom.SKIP;
//        } else {
//            m_SchedulerBody = scheduler;
//        }
//        
//        return m;
//    }
//    
//    // Add TID to function parameter and arguments
//    class TIDAdder implements PNodeVisitor {
//        public TIDAdder() {
//        }
//
//        @Override
//        public Object visit(PGDecVar node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PProcBranch node) {
//            node.m_ifProc.accept(this);
//            node.m_elseProc.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PEvent node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PExpFuncCall node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PStatLocalVarDec node) {
//            node.m_val.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PStatAssignment node) {
//            node.m_val.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PExpAtom node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PProcAtom node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PProcCall node) {
//            node.m_paraLst.add(0, Aux.cArgTidExp);
//            return this;
//        }
//
//        @Override
//        public Object visit(PExpID node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PModel node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PGDecProc node) {
//            node.m_paraLst.add(0, Aux.cParaTid);
//            node.m_body.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PProcSeq node) {
//            node.m_procLeft.accept(this);
//            node.m_procRight.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PExpStackGet node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PProcEvent node) {
//            node.m_evt.accept(this);
//            node.m_proc.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PInclude node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PGDecChan node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PProcChannel node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PChannelRecv node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PChannelSend node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PExpOpr node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PProcParallel node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PExpStackPush node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PStatReturn node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PExpTuple node) {
//            return this;
//        }
//        
//    }
//    
//
//    // Add TID to function parameter and arguments
//    class TailProcCallProcessor implements PNodeVisitor {
//        public TailProcCallProcessor() {
//        }
//
//        @Override
//        public Object visit(PGDecVar node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PProcBranch node) {
//            node.m_ifProc.accept(this);
//            node.m_elseProc.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PEvent node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PExpFuncCall node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PStatLocalVarDec node) {
//            node.m_val.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PStatAssignment node) {
//            node.m_val.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PExpAtom node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PProcAtom node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PProcCall node) {
//            if (!node.m_name.isLibFun()) {
//                if (node.isReuseStack()) {
//                    node.m_paraLst.add(0, Aux.cTrue);
//                } else {
//                    node.m_paraLst.add(0, Aux.cFalse);
//                }
//            }
//            
//            return this;
//        }
//
//        @Override
//        public Object visit(PExpID node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PModel node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PGDecProc node) {
//            node.m_paraLst.add(0, Aux.cParaIsTailCall);
//            node.m_body.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PProcSeq node) {
//            node.m_procLeft.accept(this);
//            node.m_procRight.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PExpStackGet node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PProcEvent node) {
//            node.m_evt.accept(this);
//            node.m_proc.accept(this);
//            return this;
//        }
//
//        @Override
//        public Object visit(PInclude node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PGDecChan node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PProcChannel node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PChannelRecv node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PChannelSend node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PExpOpr node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PProcParallel node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PExpStackPush node) {
//            throw new Error("not supported");
//        }
//
//        @Override
//        public Object visit(PStatReturn node) {
//            return this;
//        }
//
//        @Override
//        public Object visit(PExpTuple node) {
//            return this;
//        }
//        
//    }
//    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
