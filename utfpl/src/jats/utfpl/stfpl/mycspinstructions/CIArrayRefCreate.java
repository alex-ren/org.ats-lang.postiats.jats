package jats.utfpl.stfpl.mycspinstructions;

public class CIArrayRefCreate extends MyCspInstruction {
    public MyCspTempID m_holder;
    public IMyCspTemp m_len;
    public IMyCspTemp m_v;
    
    public CIArrayRefCreate(MyCspTempID holder, IMyCspTemp len, IMyCspTemp v, MyCspGroup blk) {
        super(blk);
        m_holder = holder;
        m_len = len;
        m_v = v;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);
        if (m_len instanceof MyCspTempID) {
            ((MyCspTempID)m_len).updateForUsage();
        }
        if (m_v instanceof MyCspTempID) {
            ((MyCspTempID)m_v).updateForUsage();
        }
        return offset;
    }
}


