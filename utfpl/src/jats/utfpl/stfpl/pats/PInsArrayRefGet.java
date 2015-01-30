package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PInsArrayRefGet extends PIns {
    public MCSId m_holder;
    public PExp m_ref;
    public PExp m_pos;
    
    public PInsArrayRefGet(PExp ref, PExp pos, MCSId holder, boolean effect) {
    	super(effect);
        m_ref = ref;
        m_pos = pos;
        m_holder = holder;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
