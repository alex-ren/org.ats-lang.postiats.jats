package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PStatAssignment implements PStat {
    public MCSId m_name;
    public PExp m_val;
    
    // for global variable
    public PStatAssignment(MCSId name, PExp val) {
        m_name = name;
        m_val = val;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
