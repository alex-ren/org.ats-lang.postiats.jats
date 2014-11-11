package jats.utfpl.stfpl.mycspinstructions;

public abstract class MyCspInstruction {
    private MyCspGroup m_blk;
    
    public MyCspInstruction(MyCspGroup blk) {
        m_blk = blk;
    }
    
    public MyCspGroup getBlock() {
        return m_blk;
    }

    abstract public Object accept(IMyCspInsVisitor visitor);
    abstract public int process(int offset);
}
