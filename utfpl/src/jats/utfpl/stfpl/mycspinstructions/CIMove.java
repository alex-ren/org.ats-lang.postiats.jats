package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.stfpl.stype.AuxSType;

public class CIMove extends MyCspInstruction {
    public MyCspTempID m_holder;
    public IMyCspTemp m_vp;


    public CIMove(IMyCspTemp vp, MyCspTempID holder, MyCspGroup blk) {
        super(blk);
        m_holder = holder;
        m_vp = vp;
        if (AuxSType.isVoid(vp.getType())) {
        	throw new Error("This is not allowed.");
        }
    }
    
    public boolean needStack() {
        return m_holder.isEscaped();
    }
    
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);
        
        if (m_vp instanceof MyCspTempID) {
            ((MyCspTempID)m_vp).updateForUsage();
        }
        
        return offset;
        
    }
}


