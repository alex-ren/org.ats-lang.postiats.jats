package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.mcinstruction.MCAtomValue;

public class MyCspTempVal implements IMyCspTemp {

    private MCAtomValue m_v;

    public MyCspTempVal(MCAtomValue v) {
        m_v = v;
    }
    
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

}
