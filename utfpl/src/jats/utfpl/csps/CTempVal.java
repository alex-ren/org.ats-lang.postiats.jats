package jats.utfpl.csps;

import jats.utfpl.instruction.AtomValue;

public class CTempVal implements CTemp {
    public AtomValue m_v;
    
    public CTempVal(AtomValue v) {
        m_v = v;
        
    }
    
    public Object accept(CSPSVisitor visitor, CBlock curBlk) {
        return visitor.visit(this, curBlk);
    }

}
