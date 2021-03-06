package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PInsMCSet implements PIns {
	
	public MCSId m_gname;
	public PExp m_v;
	
	PInsMCSet(MCSId gname, PExp v) {
		m_gname = gname;
		m_v = v;
	}

	@Override
	public Object accept(PNodeVisitor visitor) {
		return visitor.visit(this);
	}

}
