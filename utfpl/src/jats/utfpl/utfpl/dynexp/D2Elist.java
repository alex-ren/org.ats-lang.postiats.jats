package jats.utfpl.utfpl.dynexp;

import java.util.List;

public class D2Elist implements Id2exp_node {
    public int m_npf;
    public List<Cd2exp> m_d2es;
    
    
    public D2Elist(int npf, List<Cd2exp> d2es) {
        m_npf = npf;
        m_d2es = d2es;
    }

}
