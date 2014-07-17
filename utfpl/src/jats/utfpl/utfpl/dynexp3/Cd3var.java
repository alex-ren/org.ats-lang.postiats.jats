package jats.utfpl.utfpl.dynexp3;

import jats.utfpl.utfpl.Cstamp;
import jats.utfpl.utfpl.Csymbol;
import jats.utfpl.utfpl.stype.ISType;

public class Cd3var {
    public Csymbol m_sym;
    public Cstamp m_stamp;
    public ISType m_stype;
    
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    public Csymbol getSymbol() {
        return m_sym;
    }
    
    public Cd3var(Csymbol sym, Cstamp stamp, ISType stype) {
        m_sym = sym;
        m_stamp = stamp;
        m_stype = stype;
    }

}
