package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

import java.util.List;


public class PInsMCVLockViewGet extends PIns {
	
	public List<PExp> m_args;
	public MCSId m_holder;
	
	PInsMCVLockViewGet(List<PExp> args, MCSId holder, boolean effect) {
    	super(effect);
		m_args = args;
		m_holder = holder;
	}

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}
