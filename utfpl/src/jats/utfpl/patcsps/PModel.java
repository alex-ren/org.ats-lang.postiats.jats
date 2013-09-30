package jats.utfpl.patcsps;


import jats.utfpl.instruction.TID;
import jats.utfpl.patcsps.Aux.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PModel implements PNode {
    public List<PGDecVar> m_gvLst;
    public PProc m_mainProcBody;
    public List<PGDecProc> m_procLst;
    
    public List<PGDecProc> m_threadLst;
    public PProc m_SchedulerBody;
    
//    private List<PInclude> m_inclLst;
//    private List<PGDec> m_sysGVarLst;

    public PModel(List<PGDecVar> gvLst, PProc mainProcBody, List<PGDecProc> procLst) {
        m_gvLst = gvLst;
        m_mainProcBody = mainProcBody;
        m_procLst = procLst;
        
        m_threadLst = new ArrayList<PGDecProc>();
        m_SchedulerBody = null;
        
//        m_inclLst = new ArrayList<PInclude>();
//        m_sysGVarLst = new ArrayList<PGDec>();
    }
    
    public void complete() {
        // Put into patscsp.stg ===================
//    	m_inclLst.add(new PInclude("PStack"));
    	
//    	m_sysGVarLst.add(PGDecVar.createInit(Aux.cSysTid, PExpAtom.createFromInt(0)));
//    	m_sysGVarLst.add(new PGDecChan(Aux.cSysSch, 0));
//    	m_sysGVarLst.add(new PGDecChan(Aux.cSysSchStart, 0));
        // ========================================

    	Map<PGDecProc, Address> threadMap = this.threadize();

    	// todo
//    	// assembly: function address for createThread
//    	Map<PGDecProc, PAddress> threads = constructThread(threadFuncMap);

    }
    
    // Add tid to m_procLst as parameters and arguments.
    // Return the definition of processes for all the potential threads.
    private Map<PGDecProc, Address> threadize() {
        
        // =================================
        // Preparation for Scheduler
        //      Scheduler = SysChSch?fn.arg ->
        //      init{SysTid++; var tid = SysTid; GStack.allocateStack(tid);} ->
        //      ifa (fn == 0) {
        //        main1_s (SysTid, arg) ||| SchedulerW (SysTid)
        //      } else ifa (fn == 1) {
        //        fact_15_s (SysTid, arg) ||| SchedulerW (SysTid)
        //      }
        //      ;
        PProc scheduler = null;
        PExpID fn = new PExpID(TID.createPara("fn", true));
        PExpID arg = new PExpID(TID.createPara("arg", true));
        
        List<PExp> argLst = new ArrayList<PExp>();
        
        // e.g. SchedulerW (SysTid)
        argLst.add(Aux.cSysTidExp);
        PProcCall SchedulerW = new PProcCall(Aux.cSchedulerWTid, argLst);
        
        // e.g. (SysTid, arg)
        argLst = new ArrayList<PExp>();
        argLst.add(Aux.cSysTidExp);
        argLst.add(arg);
        
        // =================================
        
        Map<PGDecProc, Address> m = new HashMap<PGDecProc, Address>();
        TIDAdder processor = new TIDAdder();
        
        m_mainProcBody.accept(processor);
        
        for (PGDecProc proc: m_procLst) {
            proc.accept(processor);
            // These processes can be used as functions for creating threads.
            if (proc.m_paraLst.size() == 2) {
                List<PExp> threadArgLst = new ArrayList<PExp>();
                for (TID threadArg: proc.m_paraLst) {
                    threadArgLst.add(new PExpID(threadArg));
                }
                PProcChannel threadBody = new PProcChannel(Aux.cThreadHeader, new PProcCall(proc.m_name, threadArgLst));
                TID threadName = TID.createLibFun(proc.m_name.getID() + "_s", true);
                PGDecProc threadDef = new PGDecProc(threadName, proc.m_paraLst, new ArrayList<TID>() /*no escaped parameter*/, threadBody);

                Aux.Address addr = Aux.Address.createPointer();
                m.put(threadDef, addr);
                m_threadLst.add(threadDef);

                // e.g. fn == 0
                PExpOpr condExp = new PExpOpr(PExpOpr.Type.eq, fn, PExpAtom.createFromAddress(addr));
                // e.g. main1_s (SysTid, arg) ||| SchedulerW (SysTid)
                PProc ifProc = new PProcCall(threadName, argLst);
                ifProc = new PProcParallel(ifProc, SchedulerW);
                
                scheduler = new PProcBranch(condExp, ifProc, scheduler, PProcBranch.Type.ifa);
            }
        }
        m_SchedulerBody = scheduler;
        
        return m;
    }
    
    // Add TID to function parameter and arguments
    class TIDAdder implements PNodeVisitor {
        public TIDAdder() {
        }

        @Override
        public Object visit(PGDecVar node) {
            return this;
        }

        @Override
        public Object visit(PProcBranch node) {
            node.m_ifProc.accept(this);
            node.m_elseProc.accept(this);
            return this;
        }

        @Override
        public Object visit(PEvent node) {
            return this;
        }

        @Override
        public Object visit(PExpFuncCall node) {
            return this;
        }

        @Override
        public Object visit(PStatLocalVarDec node) {
            node.m_val.accept(this);
            return this;
        }

        @Override
        public Object visit(PStatAssignment node) {
            node.m_val.accept(this);
            return this;
        }

        @Override
        public Object visit(PExpAtom node) {
            return this;
        }

        @Override
        public Object visit(PProcAtom node) {
            return this;
        }

        @Override
        public Object visit(PProcCall node) {
            node.m_paraLst.add(0, Aux.cArgTidExp);
            return this;
        }

        @Override
        public Object visit(PExpID node) {
            return this;
        }

        @Override
        public Object visit(PModel node) {
            throw new Error("not supported");
        }

        @Override
        public Object visit(PGDecProc node) {
            node.m_paraLst.add(0, Aux.cParaTid);
            node.m_body.accept(this);
            return this;
        }

        @Override
        public Object visit(PProcSeq node) {
            node.m_procLeft.accept(this);
            node.m_procRight.accept(this);
            return this;
        }

        @Override
        public Object visit(PExpStackOpr node) {
            return this;
        }

        @Override
        public Object visit(PProcEvent node) {
            node.m_evt.accept(this);
            node.m_proc.accept(this);
            return this;
        }

        @Override
        public Object visit(PInclude node) {
            throw new Error("not supported");
        }

        @Override
        public Object visit(PGDecChan node) {
            throw new Error("not supported");
        }

        @Override
        public Object visit(PProcChannel node) {
            throw new Error("not supported");
        }

        @Override
        public Object visit(PChannelRecv node) {
            throw new Error("not supported");
        }

        @Override
        public Object visit(PChannelSend node) {
            throw new Error("not supported");
        }

        @Override
        public Object visit(PExpOpr node) {
            throw new Error("not supported");
        }

        @Override
        public Object visit(PProcParallel node) {
            throw new Error("not supported");
        }

        @Override
        public Object visit(PExpStackPush node) {
            throw new Error("not supported");
        }

        @Override
        public Object visit(PStatReturn node) {
            return this;
        }

        @Override
        public Object visit(PExpTuple node) {
            return this;
        }
        

    }
    
//    private void assemblyFuncPtr() {
//        
//    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
