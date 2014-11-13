package jats.utfpl.stfpl.pats;

import jats.utfpl.instruction.TID;

public class PExpStackGet implements PExp {
    
    public int m_pos;
    public TID m_tid;
    
    public PExpStackGet(int pos, TID tid) {
        m_pos = pos;
        m_tid = tid;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
