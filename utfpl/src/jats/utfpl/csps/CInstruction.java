package jats.utfpl.csps;

public abstract class CInstruction {
    private CBlock m_blk;
    
    public CInstruction(CBlock blk) {
        m_blk = blk;
    }
    
    public CBlock getBlock() {
        return m_blk;
    }

    abstract public Object accept(CSPSVisitor visitor);
    abstract public int process(int offset);
}
