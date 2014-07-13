package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.Cstamp;
import jats.utfpl.utfpl.Csymbol;
import jats.utfpl.utfpl.stype.ISType;

public class Cd2var {
    public Csymbol m_sym;
    public Cstamp m_stamp;
    
    private ISType m_stype;
    
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    public Csymbol getSymbol() {
        return m_sym;
    }
    
    public Cd2var(Csymbol sym, Cstamp stamp) {
        m_sym = sym;
        m_stamp = stamp;
        
        m_stype = null;
    }
    
    public void updateSType(ISType stype) {
        if (null != m_stype) {
            m_stype.match(stype);
        } else {
            m_stype = stype;
        }
    }
    
    public ISType getSType() {
        return m_stype;
    }

}
