package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PExpID implements PExp {
    public MCSId m_tid;
    
    public PExpID(MCSId tid) {
        m_tid = tid;
    }
    

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
