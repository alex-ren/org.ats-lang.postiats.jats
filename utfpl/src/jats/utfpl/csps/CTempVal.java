package jats.utfpl.csps;

import jats.utfpl.instruction.AtomValue;
import jats.utfpl.instruction.TupleValue;
import jats.utfpl.instruction.ValPrim;

public class CTempVal implements CTemp {
    public static enum type {atom, tuple};
    
    private ValPrim m_v;
    public type m_type;
    
    public CTempVal(AtomValue v) {
        m_v = v;
        m_type = type.atom;
    }
    
    public CTempVal(TupleValue v) {
        m_v = v;
        m_type = type.tuple;
    }
    
    public AtomValue getAtomValue() {
        return (AtomValue)m_v;
    }

    public TupleValue getTupleValue() {
        return (TupleValue)m_v;
    }
    
    @Override
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

}
