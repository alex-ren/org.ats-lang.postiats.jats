package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.stype.AuxSType;

public class CIReturn extends MyCspInstruction {
    
    public IMyCspTemp m_v;

    public CIReturn(IMyCspTemp v, MyCspGroup blk) {
        super(blk);
        m_v = v;
        
        if (AuxSType.isVoid(v.getType())) {
        	throw new Error("This is not allowed.");
        }
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        if (m_v instanceof MyCspTempID) {
            ((MyCspTempID)m_v).updateForUsage();
        }

        // The return value would be put onto the stack.
        return offset + 1;
        
    }

}
