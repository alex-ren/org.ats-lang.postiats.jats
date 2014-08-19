package jats.utfpl.stfpl.staexp;

import jats.utfpl.stfpl.Csymbol;

public class S2RTbas implements Is2rt {

    public Csymbol m_sym;
    
    public S2RTbas(Csymbol sym) {
        m_sym = sym;
    }

    @Override
    public boolean isType() {
        if (m_sym.equals("type") || m_sym.equals("t@ype")) {
            return true;
        } else {
            return false;
        }
    }

}
