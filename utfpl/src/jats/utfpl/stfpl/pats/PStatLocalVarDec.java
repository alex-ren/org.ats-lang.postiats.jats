package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PStatLocalVarDec implements PStat {
    public MCSId m_name;
    public PExp m_val;  // Can be null.
    
    public PStatLocalVarDec(MCSId name, PExp val) {
        m_name = name;
        m_val = val;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
