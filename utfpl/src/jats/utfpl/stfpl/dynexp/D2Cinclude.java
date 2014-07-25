package jats.utfpl.stfpl.dynexp;

import java.util.List;

public class D2Cinclude implements Id2ecl_node {
    public int m_knd;  // What is this for?
    public List<Cd2ecl> m_d2cs;
    
    public D2Cinclude(int knd, List<Cd2ecl> d2cs) {
        m_knd = knd;
        m_d2cs = d2cs;
    }

}

