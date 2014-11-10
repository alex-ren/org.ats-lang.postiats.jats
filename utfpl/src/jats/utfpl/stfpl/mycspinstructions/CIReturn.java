package jats.utfpl.stfpl.mycspinstructions;

public class CIReturn extends MyCspInstruction {
    
    public IMyCspTemp m_v;

    public CIReturn(IMyCspTemp v, MyCspGroup blk) {
        super(blk);
        m_v = v;
    }

    @Override
    public Object accept(IMyCspVisitor visitor) {
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
