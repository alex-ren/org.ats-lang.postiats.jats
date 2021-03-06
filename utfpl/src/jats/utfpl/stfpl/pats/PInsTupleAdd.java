package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PInsTupleAdd implements PExp {
    
    public MCSId m_tupname;
    public PExp  m_v;
    public int m_ind;
    
    public PInsTupleAdd(MCSId tupname, PExp v, int ind) {
        m_tupname = tupname;
        m_v = v;
        m_ind = ind;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }
	

}
