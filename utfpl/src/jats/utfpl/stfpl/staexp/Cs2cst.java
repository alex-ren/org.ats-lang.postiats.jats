package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.Csymbol;


/*
 * abstype will introduce Cs2cst
 */
public class Cs2cst {
    public Cstamp m_stamp;
    public Csymbol m_symbol;
    
    public Is2rt m_srt;
    
    // todo 
    // s2cst_supcls
    
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    public Csymbol getSymbol() {
        return m_symbol;
    }
    
    public Cs2cst(Cstamp stamp, Csymbol symbol, Is2rt srt) {
        m_stamp = stamp;
        m_symbol = symbol;
        m_srt = srt;
    }
    
    public String toStringWithStamp() {
        return m_symbol.toString() + m_stamp.getData();
    }
    
    public String toStringNoStamp() {
        return m_symbol.toString();
    }
    
}
