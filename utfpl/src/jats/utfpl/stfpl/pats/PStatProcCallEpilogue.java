package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PStatProcCallEpilogue implements PStat {

    public MCSId m_funlab;  // Don't support function pointer.
    public MCSId m_ret;  // The holder for the return value of the function call.
    
    public PStatProcCallEpilogue(MCSId funlab, MCSId ret) {
        m_funlab = funlab;
        m_ret = ret;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
