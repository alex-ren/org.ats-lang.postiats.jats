package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

import java.util.List;

public class PGDecProc implements PGDec {
    public MCSId m_name;
    public List<MCSId> m_paraLst;
    public PProc m_body;
    
    public PGDecProc(MCSId name, List<MCSId> paraLst, PProc body) {
        m_name = name;
        m_paraLst = paraLst;
        m_body = body;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
