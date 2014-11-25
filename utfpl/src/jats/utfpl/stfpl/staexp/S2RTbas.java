package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.Csymbol;
import jats.utfpl.stfpl.stype.ESort;

public class S2RTbas implements Is2rt {

    public Csymbol m_sym;
    
    public S2RTbas(Csymbol sym) {
        m_sym = sym;
    }

    @Override
    public ESort simplify() {
        return ESort.fromString(m_sym.m_str);
    }
    
    @Override
    public String toString() {
        return m_sym.toString();
    }

    @Override
    public boolean isType() {
        switch (this.simplify())
        {
        case type:
        case t0ype:
        case vtype:
        case vt0ype:
            return true;
        default:
            return false;
        }
    }

}
