package jats.utfpl.csps;

public interface CInstruction {

    public Object accept(CSPSVisitor visitor);
    public CBlock getBlock();
    
    public int process(int level);
}
