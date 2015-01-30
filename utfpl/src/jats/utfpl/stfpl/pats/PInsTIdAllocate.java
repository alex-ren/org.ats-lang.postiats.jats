package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PInsTIdAllocate extends PIns {
    
    public MCSId m_holder;
    
    public PInsTIdAllocate(MCSId holder) {
        m_holder = holder;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
