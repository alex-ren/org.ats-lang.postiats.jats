package jats.utfpl.stfpl.pats;

import jats.utfpl.instruction.TID;

public class PInsCondAlloc implements PIns {
    
    public TID m_holder;
    
    public PInsCondAlloc(TID holder) {
        m_holder = holder;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
