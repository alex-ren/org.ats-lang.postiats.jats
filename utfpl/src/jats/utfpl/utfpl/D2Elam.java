package jats.utfpl.utfpl;

import java.util.List;

public class D2Elam implements Id2exp_node {
    public List<Cp2at> m_p2ts;
    public Cd2exp m_d2exp;
    
    public D2Elam(List<Cp2at> p2ts, Cd2exp d2exp) {
        m_p2ts = p2ts;
        m_d2exp = d2exp;
    }

}
