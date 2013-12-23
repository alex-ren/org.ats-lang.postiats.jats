package jats.utfpl.patcsps;

public class PInsCondRelease implements PIns {
	public PExp m_cond;
	
	public PInsCondRelease(PExp cond) {
		m_cond = cond;
	}

	@Override
    public Object accept(PNodeVisitor visitor) {
	    return visitor.visit(this);
    }
}
