package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.mcinstruction.MCAtomValue;

public class MyCspTempVal implements IMyCspTemp {

    private MCAtomValue m_v;

    public MyCspTempVal(MCAtomValue v) {
        m_v = v;
    }
    
    public MCAtomValue getMCAtomValue() {
    	return m_v;
    }
    
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

}
