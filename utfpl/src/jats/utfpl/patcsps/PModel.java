package jats.utfpl.patcsps;


import jats.utfpl.patcsps.Aux.Address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PModel implements PNode {
    public List<PGDecVar> m_gvLst;
    public PGDecProc m_mainProc;
    public List<PGDecProc> m_procLst;
    
    private List<PInclude> m_inclLst;
    private List<PGDec> m_sysGVarLst;
    
    

    public PModel(List<PGDecVar> gvLst, PGDecProc mainProc, List<PGDecProc> procLst) {
        m_gvLst = gvLst;
        m_mainProc = mainProc;
        m_procLst = procLst;
        
        
        m_inclLst = new ArrayList<PInclude>();
        m_sysGVarLst = new ArrayList<PGDec>();
    }
    
    public void complete() {
    	m_inclLst.add(new PInclude("PStack"));
    	
    	m_sysGVarLst.add(PGDecVar.createInit(Aux.cSysTid, PExpAtom.createFromInt(0)));
    	m_sysGVarLst.add(new PGDecChan(Aux.cSysSch, 0));
    	m_sysGVarLst.add(new PGDecChan(Aux.cSysSchStart, 0));
    	
    	// add tid to process definition and invocation, m_procLst has been updated
    	Map<PGDecProc, Address> threadFuncMap = this.threadize();
    	
//    	// assembly: function address for createThread
//    	Map<PGDecProc, PAddress> threads = constructThread(threadFuncMap);
//    	
//    	createScheduler(threads);
//    	
//    	createSchedulerW();
    	
    	// new Stack();
    	
    	
    	
    	
    }
    
    // Add tid to m_procLst. Return all the possbile candicates for creating threads.
    private Map<PGDecProc, Address> threadize() {
        Map<PGDecProc, Address> m = new HashMap<PGDecProc, Address>();
        TIDAdder processor = new TIDAdder();
        for (PGDecProc proc: m_procLst) {
            processor.transform(proc);
            if (proc.m_paraLst.size() == 2) {
                m.put(proc, Aux.Address.createPointer());
            }
        }
        
        return m;
    }
    
    class TIDAdder implements PNodeVisitor {
        public TIDAdder() {
        }
        
        /*
         * Update the proc deeply.
         */
        public void transform(PGDecProc proc) {
            proc.accept(this);
            return;            
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
            node.m_exp.accept(this);
            return this;
        }

        @Override
        public Object visit(PStatAssignment node) {
            node.m_exp.accept(this);
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
            node.m_paraLst.add(0, new PExpID(Aux.cParaTid));
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
        

    }
    
//    private void assemblyFuncPtr() {
//        
//    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
