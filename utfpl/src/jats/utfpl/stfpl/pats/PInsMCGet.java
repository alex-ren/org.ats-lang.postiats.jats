package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PInsMCGet extends PIns {
	
	public MCSId m_gv;
	public MCSId m_holder;
	
	PInsMCGet(MCSId gv, MCSId holder, boolean effect) {
    	super(effect);
		m_gv = gv;
		m_holder = holder;
	}

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}


