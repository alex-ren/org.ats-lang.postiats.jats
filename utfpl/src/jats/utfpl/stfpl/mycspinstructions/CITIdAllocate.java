package jats.utfpl.stfpl.mycspinstructions;

public class CITIdAllocate extends MyCspInstruction {
    public MyCspTempID m_holder;
    
    public CITIdAllocate(MyCspTempID holder, MyCspGroup blk) {
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
