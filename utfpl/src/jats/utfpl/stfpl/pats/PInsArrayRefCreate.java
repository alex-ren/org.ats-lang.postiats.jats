package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PInsArrayRefCreate extends PIns {
    
    public MCSId m_holder;
    public PExp m_v;  // initial value
    public PExp m_len;
    
    public PInsArrayRefCreate(MCSId holder, PExp len, PExp v, boolean effect) {
    	super(effect);
        m_holder = holder;
        m_len = len;
        m_v = v;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
