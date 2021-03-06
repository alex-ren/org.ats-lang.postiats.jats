package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.Cstamp;
import jats.utfpl.stfpl.Csymbol;

/*
 * Two Cs2var's have different Cstamp.
 */
public class Cs2var {
    public Is2rt m_srt;
    public Csymbol m_sym;
    public Cstamp m_stamp;
    
    @Override
    public String toString() {
        return m_sym.toString();
    }
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    public Csymbol getSymbol() {
        return m_sym;
    }
    
    public Cs2var(Csymbol sym, Cstamp stamp, Is2rt srt) {
        m_sym = sym;
        m_stamp = stamp;
        m_srt = srt;
    }
    
    public boolean isType() {
        return m_srt.isType();
    }

}
