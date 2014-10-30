package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.instructions.SId;
import jats.utfpl.stfpl.stype.ISType;

public class MCSIdAtom implements IMCIdPrim {
	private SId m_sid;

	public MCSIdAtom(SId sid) {
		m_sid = sid;
	}
	
	@Override
    public ISType getType() {
	    return m_sid.getType();
    }
}
