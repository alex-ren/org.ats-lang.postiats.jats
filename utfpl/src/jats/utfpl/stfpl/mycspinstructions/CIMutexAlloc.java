package jats.utfpl.stfpl.mycspinstructions;

public class CIMutexAlloc extends MyCspInstruction {
    public MyCspTempID m_holder;
    
    public CIMutexAlloc(MyCspTempID holder, MyCspGroup blk) {
        super(blk);
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
