package jats.utfpl.utfpl.dynexp3;

import java.util.List;

public class D3Etup implements Id3exp_node {
    public int m_knd;  // 0: flat, 1: boxed
    public List<Cd3exp> m_d2es;
    
    public D3Etup(int knd, List<Cd3exp> d2es) {
        m_knd = knd;
        m_d2es = d2es;
    }


}
