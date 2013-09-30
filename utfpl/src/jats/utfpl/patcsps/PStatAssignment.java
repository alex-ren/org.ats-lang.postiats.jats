package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class PStatAssignment implements PStat {
    public TID m_name;
    public PExp m_val;
    
    // for global variable
    public PStatAssignment(TID name, PExp val) {
        if (name.isGlobal() == false) {
            throw new Error("Unexpected");
        }
        m_name = name;
        m_val = val;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
