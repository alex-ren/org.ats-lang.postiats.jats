package jats.utfpl.stfpl.mycspinstructions;

public abstract class MyCspInstruction {
    private MyCspGroup m_blk;
    private boolean m_effect;
    
    public MyCspInstruction(MyCspGroup blk, boolean effect) {
        m_blk = blk;
        m_effect = effect;
    }
    
    public boolean hasSideEffect() {
    	return m_effect;
    }
    
    public MyCspGroup getBlock() {
        return m_blk;
    }

    abstract public Object accept(IMyCspInsVisitor visitor);
    abstract public int process(int offset);
}
