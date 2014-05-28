package jats.utfpl.utfpl.dynexp;


import java.util.List;

public class D2ElamDyn implements Id2exp_node {
    public int m_lin;  // something to do with linear type?
    public int m_npf;  // number of proof arguments if m_npf >=0
    // m_npf can be negative, such as -1.
    public List<Cp2at> m_p2ts;  // arguments including proof and normal arguments
                                // proof arguments appear first in the list
    // These Cp2at's are actually P2Tann's.
    
    public Cd2exp m_d2exp;  // body of the function

    public D2ElamDyn(int npf, int lin, List<Cp2at> p2ts, Cd2exp d2exp) {
        m_p2ts = p2ts;
        m_d2exp = d2exp;
        m_npf = npf;
        m_lin = lin;
    }

}
