package jats.utfpl.utfpl;

public class Cd2var {
    public Csymbol m_sym;
    public Cstamp m_stamp;
    
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    public Csymbol getSymbol() {
        return m_sym;
    }
    
    public Cd2var(Csymbol sym, Cstamp stamp) {
        m_sym = sym;
        m_stamp = stamp;
    }

}