package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class PGDecChan implements PGDec {
	
	public TID m_tid;
	public int m_cap;
	
	public PGDecChan(TID tid, int cap) {
	    m_tid = tid;
	    m_cap = cap;
	}

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
