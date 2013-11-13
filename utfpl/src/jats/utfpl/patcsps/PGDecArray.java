package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class PGDecArray implements PGDec {
    public TID m_tid;
    
    public PGDecArray(TID tid) {
        m_tid = tid;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
