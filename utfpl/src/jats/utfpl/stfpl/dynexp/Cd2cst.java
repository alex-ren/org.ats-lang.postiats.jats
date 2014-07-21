package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.staexp.Cs2exp;
import jats.utfpl.stfpl.stype.ISType;

public class Cd2cst {
	// What is constant? True, False?
    // Some examples are function name (in Ci2mpdec)
    public Cstamp m_stamp;
    public Cs2exp m_type;
    public Csymbol m_symbol;
    
    public ISType m_stype;
    
    // Used for print (stringtemplate)
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    // Used for print (stringtemplate)
    public Csymbol getSymbol() {
        return m_symbol;
    }
    
    // Used for print (stringtemplate)
    public Cs2exp getType() {
        return m_type;
    }
    
    public Cd2cst(Cstamp stamp, Cs2exp type, Csymbol symbol, ISType stype) {
        m_stamp = stamp;
        m_type = type;
        m_symbol = symbol;
        m_stype = stype;
    }
    
    @Override
    public String toString() {
        return m_symbol.toString();
    }

}
