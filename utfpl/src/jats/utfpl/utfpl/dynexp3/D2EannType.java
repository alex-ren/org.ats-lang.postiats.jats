package jats.utfpl.utfpl.dynexp3;

import jats.utfpl.utfpl.staexp.Cs2exp;

public class D2EannType implements Id3exp_node {

    public Cd3exp m_d2exp;
    public Cs2exp m_s2exp;
    
    public D2EannType(Cd3exp d2exp, Cs2exp s2exp) {
        m_d2exp = d2exp;
        m_s2exp = s2exp;
    }

}
