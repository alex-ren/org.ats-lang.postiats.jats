package jats.utfpl.stfpl.pats;

import jats.utfpl.instruction.TID;

public class PStatLocalVarDec implements PStat {
    public TID m_name;
    public PExp m_val;
    
    public PStatLocalVarDec(TID name, PExp val) {
        m_name = name;
        m_val = val;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
