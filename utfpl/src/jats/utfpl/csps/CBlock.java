package jats.utfpl.csps;

public abstract class CBlock {
    static public enum Type{evt, proc};
    
    abstract Object accept(CSPSVisitor visitor);
    
    abstract int process(int offset);
    
    protected int m_level;
    
    public CBlock(int level) {
        m_level = level;
    }

}
