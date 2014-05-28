package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.staexp.Cs2cst;

import java.util.List;

public class D2Cstacsts implements Id2ecl_node {
    public List<Cs2cst> m_s2csts;
    
    public D2Cstacsts(List<Cs2cst> s2csts) {
        m_s2csts = s2csts;
    }

}
