package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.instruction.AtomValue;
import jats.utfpl.instruction.TupleValue;
import jats.utfpl.instruction.ValPrim;

public class MyCspTempVal implements IMyCspTemp {
    public static enum type {atom, tuple};
    
    private ValPrim m_v;
    public type m_type;
    
    public MyCspTempVal(AtomValue v) {
        m_v = v;
        m_type = type.atom;
    }
    
    public MyCspTempVal(TupleValue v) {
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
    public Object accept(IMyCspVisitor visitor) {
        return visitor.visit(this);
    }

}
