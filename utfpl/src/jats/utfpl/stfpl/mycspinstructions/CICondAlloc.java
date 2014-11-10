package jats.utfpl.stfpl.mycspinstructions;

public class CICondAlloc extends MyCspInstruction {
    public MyCspTempID m_holder;
    
    public CICondAlloc(MyCspTempID holder, MyCspGroup blk) {
        super(blk);
        m_holder = holder;
    }

    @Override
    public Object accept(IMyCspVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public int process(int offset) {
        offset = m_holder.processStack(offset);
        return offset;
    }
}

