package jats.utfpl.patcsps;

import java.util.ArrayList;
import java.util.List;

public class PModel implements PNode {
    public List<PGDecVar> m_gvLst;
    public PGDecProc m_mainProc;
    public List<PGDecProc> m_procLst;
    
    private List<PInclude> m_inclLst;
    private List<PGDecVar> m_sysGVarLst;
    
    

    public PModel(List<PGDecVar> gvLst, PGDecProc mainProc, List<PGDecProc> procLst) {
        m_gvLst = gvLst;
        m_mainProc = mainProc;
        m_procLst = procLst;
        
        
        m_inclLst = new ArrayList<PInclude>();
        m_sysGVarLst = new ArrayList<PGDecVar>();
    }
    
    public void complete() {
    	m_inclLst.add(new PInclude("PStack"));
    	
    	m_sysGVarLst.add(PGDecVar.createInit(Aux.cGTid, PExpAtom.createFromInt(0)));
    	
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
