package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.stype.ISType;

/*
 * I don't care much about the identity of the object.
 */
public class Cd3sym {
    public Csymbol m_name;
    
    public ISType m_stype;
    
    public Cd3sym(Csymbol name, ISType stype) {
        m_name = name;
        m_stype = stype;
    }
    
    @Override
    public String toString() {
        return m_name.toString();
    }
}
