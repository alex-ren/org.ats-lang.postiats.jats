package jats.utfpl.utfpl;

import java.util.List;

public class D2Cdcstdecs implements Id2ecl_node {
    public Edcstkind m_knd;
    public List<Cd2cst> m_d2cst;
    
    public D2Cdcstdecs(Edcstkind knd, List<Cd2cst> d2cst) {
        m_knd = knd;
        m_d2cst = d2cst;
    }
}
