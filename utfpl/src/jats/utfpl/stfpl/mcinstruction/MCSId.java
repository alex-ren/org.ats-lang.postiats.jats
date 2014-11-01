package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.stype.ISType;

public class MCSId implements IMCValPrim {
	private SId m_sid;

	public MCSId(SId sid) {
		m_sid = sid;
	}
	
	@Override
    public ISType getType() {
	    return m_sid.getType();
    }
}
