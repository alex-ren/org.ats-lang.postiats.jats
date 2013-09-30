package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class PExpStackOpr implements PExp {
    
    public int m_frame;
    public int m_pos;
    public TID m_tid;
    
    public PExpStackOpr(int frame, int pos, TID tid) {
        m_frame = frame;
        m_pos = pos;
        m_tid = tid;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
