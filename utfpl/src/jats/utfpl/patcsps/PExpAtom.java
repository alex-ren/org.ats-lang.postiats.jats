package jats.utfpl.patcsps;

import jats.utfpl.instruction.AtomValue;

public class PExpAtom implements PExp {
    public AtomValue m_v;
    
    public PExpAtom(AtomValue v) {
        m_v = v;
    }
    
    public static PExpAtom createFromInt(int x) {
    	return new PExpAtom(AtomValue.createFromInt(x));
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
