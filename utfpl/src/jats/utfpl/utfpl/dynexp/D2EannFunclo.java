package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.staexp.Ifunclo;

public class D2EannFunclo implements Id2exp_node {
    
    public Cd2exp m_d2exp;
    public Ifunclo m_funclo;
    
    public D2EannFunclo(Cd2exp d2exp, Ifunclo funclo) {
        m_d2exp = d2exp;
        m_funclo = funclo;
    }

}
