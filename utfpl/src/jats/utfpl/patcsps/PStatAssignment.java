package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class PStatAssignment implements PStat {
    public TID m_var;
    public PExp m_exp;
    
    public PStatAssignment(TID var, PExp exp) {
        m_var = var;
        m_exp = exp;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
