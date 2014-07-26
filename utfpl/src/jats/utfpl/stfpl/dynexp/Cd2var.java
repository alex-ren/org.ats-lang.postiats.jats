package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.stype.ISType;

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
    
    public void normalizeType() {
        m_stype = m_stype.normalize();
    }
    
    public ISType getSType() {
        return m_stype;
    }

}
