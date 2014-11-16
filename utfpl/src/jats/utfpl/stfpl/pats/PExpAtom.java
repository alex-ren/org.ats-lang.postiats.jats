package jats.utfpl.stfpl.pats;


import jats.utfpl.stfpl.mcinstruction.AuxMCIns.Address;
import jats.utfpl.stfpl.mcinstruction.MCAtomValue;

public class PExpAtom implements PExp {
    public MCAtomValue m_v;
    
    private PExpAtom(MCAtomValue v) {
        m_v = v;
    }
    
    public static PExpAtom createFromAtomValue(MCAtomValue v) {
        return new PExpAtom(v);
    }
    
    public static PExpAtom createFromInt(int x) {
    	return new PExpAtom(MCAtomValue.createFromInt(x));
    }
    
    public static PExpAtom createFromBoolean(boolean x) {
        return new PExpAtom(MCAtomValue.createFromBoolean(x));
    }
    
    public static PExpAtom createFromAddress(Address addr) {
        return new PExpAtom(MCAtomValue.createFromInt(addr.getValue()));
    }
    
    public String toStringMCIns() {
        return m_v.toStringMCIns();
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
