package jats.utfpl.utfpl.staexp;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.Csymbol;


/*
 * abstype will introduce Cs2cst
 */
public class Cs2cst {
    public Cstamp m_stamp;
    public Csymbol m_symbol;
    
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    public Csymbol getSymbol() {
        return m_symbol;
    }
    
    public Cs2cst(Cstamp stamp, Csymbol symbol) {
        m_stamp = stamp;
        m_symbol = symbol;
    }
    
    public String toString() {
        return "Cs2cst: " + m_symbol.toString();
    }
    
}
