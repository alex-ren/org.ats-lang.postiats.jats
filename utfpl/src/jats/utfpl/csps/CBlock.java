package jats.utfpl.csps;

public abstract class CBlock {
    static public enum Type{evt, proc};
    
    public abstract Object accept(CSPSVisitor visitor);
    
    public abstract int process(int offset);
    
    protected int m_level;
    
    public int getLevel() {
    	return m_level;
    }
    
    public CBlock(int level) {
        m_level = level;
    }

}
