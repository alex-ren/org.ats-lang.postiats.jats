package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class PExpStackPush implements PExp {
    public TID m_name;
    
    public PExpStackPush(TID name) {
        m_name = name;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
