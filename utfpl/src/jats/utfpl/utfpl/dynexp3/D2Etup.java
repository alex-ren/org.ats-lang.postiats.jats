package jats.utfpl.utfpl.dynexp3;

import java.util.List;

public class D2Etup implements Id3exp_node {
    public int m_knd;  // 0: flat, 1: boxed
    public int m_npf;  // no. of proof, be -1 is none
    public List<Cd3exp> m_d2es;
    
    public D2Etup(int knd, int npf, List<Cd3exp> d2es) {
        m_knd = knd;
        m_npf = npf;
        m_d2es = d2es;
    }


}
