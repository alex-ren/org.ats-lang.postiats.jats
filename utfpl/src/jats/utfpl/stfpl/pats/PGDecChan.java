package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.instructions.SId;

public class PGDecChan implements PGDec {
	
	public SId m_tid;
	public int m_cap;
	
	public PGDecChan(SId tid, int cap) {
	    m_tid = tid;
	    m_cap = cap;
	}

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
