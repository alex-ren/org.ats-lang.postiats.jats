package jats.utfpl.stfpl.mycspinstructions;

public class CIMutexCreate extends MyCspInstruction {
    public MyCspTempID m_holder;
    
    public CIMutexCreate(MyCspTempID holder, MyCspGroup blk, boolean effect) {
        super(blk, effect);
        m_holder = holder;
    }

    @Override
    public Object accept(IMyCspInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);
        return offset;
    }

}
