package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.stype.ISType;

public class Cd2sym {
    public Csymbol m_d2sym_name;
    
    private ISType m_stype;
    
    // used by StringTemplate
    public Csymbol getSymbol() {
        return m_d2sym_name;
    }
    
    public Cd2sym(Csymbol sym_name) {
        m_d2sym_name = sym_name;
        m_stype = null;
    }
    
    public ISType getSType() {
        return m_stype;
    }
    
    public void updateSType(ISType ty) {
        m_stype = ty;
    }
}
