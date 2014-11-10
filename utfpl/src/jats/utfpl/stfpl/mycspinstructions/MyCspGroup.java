package jats.utfpl.stfpl.mycspinstructions;

public abstract class MyCspGroup {
    static public enum Type{evt, proc};
    
    public abstract Object accept(IMyCspVisitor visitor);
    
    // The function is for detecting whether certain
    // variable can be used out of the current scope,
    // and for supporting such scenario using stack.
    public abstract int process(int offset);
    
    @Override
    public boolean equals(Object o) {
        throw new Error("not supported");
    }
}
