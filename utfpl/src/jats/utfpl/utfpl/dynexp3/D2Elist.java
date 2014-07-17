package jats.utfpl.utfpl.dynexp3;

import java.util.List;

public class D2Elist implements Id3exp_node {
    public int m_npf;
    public List<Cd3exp> m_d2es;
    
    
    public D2Elist(int npf, List<Cd3exp> d2es) {
        m_npf = npf;
        m_d2es = d2es;
    }

}
