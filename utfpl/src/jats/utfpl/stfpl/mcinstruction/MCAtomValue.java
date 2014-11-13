package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.instructions.AtomValue;
import jats.utfpl.stfpl.stype.ISType;

public class MCAtomValue implements IMCValPrim {
	public AtomValue m_v;
	
	public MCAtomValue(AtomValue v) {
		m_v = v;
	}

	@Override
	public ISType getType() {
		return m_v.getType();
	}

	@Override
    public String toStringMCIns() {
	    return m_v.toString();
    }

//    @Override
//    public String toStringCS() {
//        return m_v.toString();
//    }

}
