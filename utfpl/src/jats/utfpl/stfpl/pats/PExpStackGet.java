package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;


public class PExpStackGet implements PExp {
    
    public int m_pos;
    public MCSId m_tid;
    
    public PExpStackGet(int pos, MCSId tid) {
        m_pos = pos;
        m_tid = tid;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
