package jats.utfpl.stfpl.pats;

import jats.utfpl.instruction.AtomValue;
import jats.utfpl.patcsps.Aux.Address;

public class PExpAtom implements PExp {
    public AtomValue m_v;
    
    private PExpAtom(AtomValue v) {
        m_v = v;
    }
    
    public static PExpAtom createFromAtomValue(AtomValue v) {
        return new PExpAtom(v);
    }
    
    public static PExpAtom createFromInt(int x) {
    	return new PExpAtom(AtomValue.createFromInt(x));
    }
    
    public static PExpAtom createFromBoolean(boolean x) {
        return new PExpAtom(AtomValue.createFromBoolean(x));
    }
    
    public static PExpAtom createFromAddress(Address addr) {
        return new PExpAtom(AtomValue.createFromInt(addr.getValue()));
    }
    
    @Override
    public String toString() {
        return m_v.toString();
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
