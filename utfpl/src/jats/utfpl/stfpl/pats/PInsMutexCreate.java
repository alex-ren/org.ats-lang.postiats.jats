package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PInsMutexCreate extends PIns {
    
    public MCSId m_holder;
    
    public PInsMutexCreate(MCSId holder, boolean effect) {
    	super(effect);
        m_holder = holder;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
