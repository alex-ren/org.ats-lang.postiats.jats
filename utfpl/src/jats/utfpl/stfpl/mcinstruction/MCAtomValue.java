package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.instructions.AtomValue;

public class MCAtomValue implements IMCValPrim {
	public AtomValue m_v;
	private ICSType m_type;
	
	public MCAtomValue(AtomValue v, ICSType type) {
		m_v = v;
		m_type = type;
	}

	@Override
	public ICSType getType() {
		return m_type;
	}

//    @Override
//    public String toStringCS() {
//        return m_v.toString();
//    }

}
