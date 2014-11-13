package jats.utfpl.stfpl.pats;

import jats.utfpl.instruction.TID;

public class PExpID implements PExp {
    public TID m_tid;
    
    public PExpID(TID tid) {
        m_tid = tid;
    }
    

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
