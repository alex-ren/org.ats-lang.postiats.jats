package jats.utfpl.stfpl.pats;

import jats.utfpl.instruction.TID;

public class PGDecArray implements PGDec {
    public TID m_tid;
    public int m_sz;
    
    public PGDecArray(TID tid, int sz) {
        m_tid = tid;
        m_sz = sz;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
