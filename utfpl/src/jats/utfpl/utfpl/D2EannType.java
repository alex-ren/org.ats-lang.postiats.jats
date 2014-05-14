package jats.utfpl.utfpl;

public class D2EannType implements Id2exp_node {

    public Cd2exp m_d2exp;
    public Cs2exp m_s2exp;
    
    public D2EannType(Cd2exp d2exp, Cs2exp s2exp) {
        m_d2exp = d2exp;
        m_s2exp = s2exp;
    }

}
