package jats.utfpl.csps;

public abstract class CBlock {
    static public enum Type{evt, proc};
    
    public abstract Object accept(CSPSVisitor visitor);
    
    // The function is for detecting whether certain
    // variable can be used out of the current scope,
    // and for supporting such scenario using stack.
    public abstract int process(int offset);

}
