package jats.utfpl.csps;

import jats.utfpl.instruction.InsVisitor;

public interface CBlock {
    static public enum Type{evt, proc};
    
    Object accept(CSPSVisitor visitor);

}
