package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.Cstamp;
import jats.utfpl.utfpl.Csymbol;
import jats.utfpl.utfpl.staexp.Cs2exp;

public class Cd2cst {
	// What is constant? True, False?
    // Some examples are function name (in Ci2mpdec)
    public Cstamp m_stamp;
    public Cs2exp m_type;
    public Csymbol m_symbol;
    
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    public Csymbol getSymbol() {
        return m_symbol;
    }
    
    public Cs2exp getType() {
        return m_type;
    }
    
    public Cd2cst(Cstamp stamp, Cs2exp type, Csymbol symbol) {
        m_stamp = stamp;
        m_type = type;
        m_symbol = symbol;
    }

}
