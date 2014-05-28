package jats.utfpl.utfpl.staexp;

import jats.utfpl.utfpl.Cstamp;
import jats.utfpl.utfpl.Csymbol;

public class Cs2var {
    // public Is2rt m_srt;  // Currently, this field hasn't been exported from ATS.
    public Csymbol m_sym;
    public Cstamp m_stamp;
    
    public Cstamp getStamp() {
        return m_stamp;
    }
    
    public Csymbol getSymbol() {
        return m_sym;
    }
    
    public Cs2var(Csymbol sym, Cstamp stamp) {
        m_sym = sym;
        m_stamp = stamp;
    }

}
