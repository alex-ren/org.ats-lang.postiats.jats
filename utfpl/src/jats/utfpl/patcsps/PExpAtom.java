package jats.utfpl.patcsps;

import jats.utfpl.instruction.AtomValue;

public class PExpAtom implements PExp {
    public AtomValue m_v;
    
    public PExpAtom(AtomValue v) {
        m_v = v;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
