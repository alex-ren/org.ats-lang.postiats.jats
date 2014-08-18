package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.Csymbol;


/*
 * abstype will introduce Cs2cst
 */
public class Cs2cst {
    public Cstamp m_stamp;
    public Csymbol m_symbol;
    // todo 
    // s2cst_supcls
    // s2cst_srt
    
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
        return m_symbol.toString() + m_stamp.getData();
    }
    
}
