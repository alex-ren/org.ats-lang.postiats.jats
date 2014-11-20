package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;


public class PInsSharedCreateCond implements PIns {
    
    public MCSId m_holder;
    
    public PInsSharedCreateCond(MCSId holder) {
        m_holder = holder;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}