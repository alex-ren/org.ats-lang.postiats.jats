package jats.utfpl.stfpl.pats;


public class PInsMCVLockViewPut extends PIns {
	public PExp m_v;
	
	public PInsMCVLockViewPut(PExp v) {
		m_v = v;
	}

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}
}
