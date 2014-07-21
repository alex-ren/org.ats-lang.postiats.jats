package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.utfpl.stype.ISType;

public class Cd2sym {
    public Csymbol m_d2sym_name;
    
    public ISType m_stype;
    
    // used by StringTemplate
    public Csymbol getSymbol() {
        return m_d2sym_name;
    }
    
    public Cd2sym(Csymbol sym_name) {
        m_d2sym_name = sym_name;
        m_stype = null;
    }
}
