package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCGlobalExtCode;

import java.util.ArrayList;
import java.util.List;


public class PModel implements PNode {
    public List<PGDec> m_gvLst;
    public List<MCGlobalExtCode> m_extCodeLst;
    public PProc m_mainProcBody;
    public List<PGDecProc> m_procLst;  // all the processes in the program
    public List<PGDecProc> m_threadLst;  // those processes being used in creating thread
    
//    public PProc m_SchedulerBody;
//    private List<PInclude> m_inclLst;
//    private List<PGDec> m_sysGVarLst;

    public PModel(List<PGDec> gvLst, PProc mainProcBody, 
            List<PGDecProc> procLst, List<MCGlobalExtCode> extCodeLst) {
        m_gvLst = gvLst;
        m_mainProcBody = mainProcBody;
        m_procLst = procLst;
        m_extCodeLst = extCodeLst;
        
        m_threadLst = new ArrayList<PGDecProc>();
        for (PGDecProc proc: procLst) {
        	if (proc.m_name.hasAddress()) {
        		m_threadLst.add(proc);
        	}
        }
//        m_SchedulerBody = null;
        
//        m_inclLst = new ArrayList<PInclude>();
//        m_sysGVarLst = new ArrayList<PGDec>();
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
