package jats.utfpl.stfpl.mycspinstructions;

public class CIAtomRefCreate extends MyCspInstruction {
    public MyCspTempID m_holder;
    public IMyCspTemp m_vp;
    
    public CIAtomRefCreate(MyCspTempID holder, IMyCspTemp vp, MyCspGroup blk, boolean effect) {
        super(blk, effect);
        m_holder = holder;
        m_vp = vp;
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
