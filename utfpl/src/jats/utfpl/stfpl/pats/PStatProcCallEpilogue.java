package jats.utfpl.stfpl.pats;

import jats.utfpl.instruction.TID;

public class PStatProcCallEpilogue implements PStat {

    public TID m_funlab;  // Don't support function pointer.
    public TID m_ret;  // The holder for the return value of the function call.
    
    public PStatProcCallEpilogue(TID funlab, TID ret) {
        m_funlab = funlab;
        m_ret = ret;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
