package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PInsAtomRefCreate extends PIns {
    
    public MCSId m_holder;
    public PExp m_v;  // initial value
    
    public PInsAtomRefCreate(MCSId holder, PExp v) {
        m_holder = holder;
        m_v = v;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
