package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.stype.ISType;

public interface IVarName {
    public ISType getType();
    
    // print for layer of C#
    public String toStringCS();
    
    // print for layer of instruction
    public String toStringIns();
    
    public String toStringNoStamp();
    
    public String toStringWithStamp();
    
}
