package jats.utfpl.patcsps;

import java.util.List;

public class PModel implements PNode {
    public List<PGDecVar> m_gvLst;
    public PGDecProc m_mainProc;
    public List<PGDecProc> m_procLst;

    public PModel(List<PGDecVar> gvLst, PGDecProc mainProc, List<PGDecProc> procLst) {
        m_gvLst = gvLst;
        m_mainProc = mainProc;
        m_procLst = procLst;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
