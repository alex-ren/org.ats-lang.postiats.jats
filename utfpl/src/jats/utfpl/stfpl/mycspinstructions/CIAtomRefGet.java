package jats.utfpl.stfpl.mycspinstructions;

public class CIAtomRefGet extends MyCspInstruction {

    public MyCspTempID m_localHolder;
    public MyCspTempID m_ref;

    public CIAtomRefGet(MyCspTempID ref, MyCspTempID localHolder, MyCspGroup blk, boolean effect) {
        super(blk, effect);
        m_localHolder = localHolder;
        m_ref = ref;

    }
    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }
    
    @Override
    public int process(int offset) {
        offset = m_localHolder.processStack(offset);
        m_ref.updateForUsage();
        return offset;
    }

}
