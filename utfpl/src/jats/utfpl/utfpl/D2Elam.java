package jats.utfpl.utfpl;

import java.util.List;

public class D2Elam implements Id2exp_node {
    public List<Cp2at> m_p2ts;  // arguments including proof and normal arguments
                                // proof arguments appear first in the list
    public Cd2exp m_d2exp;  // body of the function
    public int m_npf;  // number of proof arguments if m_npf >=0
                       // m_npf can be negative, such as -1.
    
    public D2Elam(List<Cp2at> p2ts, Cd2exp d2exp, int npf) {
        m_p2ts = p2ts;
        m_d2exp = d2exp;
        m_npf = npf;
    }

}
