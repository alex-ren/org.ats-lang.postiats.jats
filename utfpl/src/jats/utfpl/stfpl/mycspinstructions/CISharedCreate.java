
package jats.utfpl.stfpl.mycspinstructions;


public class CISharedCreate extends MyCspInstruction {
    public MyCspTempID m_holder;
    public IMyCspTemp m_vp;
    public IMyCspTemp m_n_cond;

    public CISharedCreate(MyCspTempID holder, IMyCspTemp vp, IMyCspTemp n_cond, MyCspGroup blk, boolean effect) {
        super(blk, effect);
        m_holder = holder;
        m_vp = vp;
        m_n_cond = n_cond;
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
        if (m_n_cond instanceof MyCspTempID) {
            ((MyCspTempID)m_n_cond).updateForUsage();
        }
        return offset;
    }
}
