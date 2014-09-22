package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.stype.ISType;

public interface IVarName {
    public ISType getType();
    
    public String toStringCS();
    
    // print for layer of instruction
    public String toStringIns();
    
}
