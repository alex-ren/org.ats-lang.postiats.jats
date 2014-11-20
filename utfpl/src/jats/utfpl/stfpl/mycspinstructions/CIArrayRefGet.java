package jats.utfpl.stfpl.mycspinstructions;

public class CIArrayRefGet extends MyCspInstruction {

    public MyCspTempID m_ref;
    public IMyCspTemp m_pos;
    public MyCspTempID m_holder;

    public CIArrayRefGet(MyCspTempID ref, IMyCspTemp pos, MyCspTempID holder, MyCspGroup blk) {
        super(blk);
        m_ref = ref;
        m_pos = pos;
        m_holder = holder;

    }
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);
        
        if (m_pos instanceof MyCspTempID) {
            ((MyCspTempID)m_pos).updateForUsage();
        }
        
        return offset;
    }

}
