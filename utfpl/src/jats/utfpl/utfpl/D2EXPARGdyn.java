package jats.utfpl.utfpl;

import java.util.List;

public class D2EXPARGdyn implements Id2exparg {
    // Example: (pf1, pf2 | arg1, arg2, arg3)
    public int m_i;  // The number of proof arguments if not zero.
    public Cloc_t m_loc;
    public List<Cd2exp> m_d2expLst;  // List of arguments.
    
    public D2EXPARGdyn(int i, Cloc_t loc, List<Cd2exp> d2expLst) {
        m_i = i;
        m_loc = loc;
        m_d2expLst = d2expLst;
    }

}



