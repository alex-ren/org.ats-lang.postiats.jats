package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.instructions.AtomValue;

public class CSAtomValue implements ICSValPrim {
	public AtomValue m_v;
	private ICSType m_type;
	
	public CSAtomValue(AtomValue v, ICSType type) {
		m_v = v;
		m_type = type;
	}

	@Override
	public ICSType getType() {
		return m_type;
	}

    @Override
    public String toStringCS() {
        return m_v.toString();
    }

}
