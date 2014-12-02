package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PInsSharedCreate implements PIns {
    
    public MCSId m_holder;
	public PExp m_v;
	public PExp m_n;
    
    public PInsSharedCreate(MCSId holder, PExp v, PExp n) {
        m_holder = holder;
        m_v = v;
        m_n = n;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
