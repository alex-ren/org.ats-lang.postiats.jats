package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PInsMutexCreate implements PIns {
    
    public MCSId m_holder;
    
    public PInsMutexCreate(MCSId holder) {
        m_holder = holder;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
