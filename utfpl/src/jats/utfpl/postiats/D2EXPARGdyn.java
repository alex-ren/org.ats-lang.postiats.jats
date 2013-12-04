package jats.utfpl.postiats;

import java.util.List;

public class D2EXPARGdyn implements Id2exparg {
    public int m_i;
    public Cloc_t m_loc;
    public List<Cd2exp> m_d2expLst;
    
    public D2EXPARGdyn(int i, Cloc_t loc, List<Cd2exp> d2expLst) {
        m_i = i;
        m_loc = loc;
        m_d2expLst = d2expLst;
    }

}



