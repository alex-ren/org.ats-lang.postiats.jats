package jats.utfpl.stfpl.pats;

import java.util.List;


public class PInsMCVLockViewGet implements PIns {
	
	public List<PExp> m_args;
	
	PInsMCVLockViewGet(List<PExp> args) {
		m_args = args;
	}

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}
