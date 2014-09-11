package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.Cloc_t;

import java.util.List;

public class D2EXPARGdyn implements Id2exparg {
    // Example: (pf1, pf2 | arg1, arg2, arg3)
    public int m_npf;  // The number of proof arguments if not zero.
    public Cloc_t m_loc;
    public List<Cd2exp> m_d2expLst;  // List of arguments.
    
    public D2EXPARGdyn(int npf, Cloc_t loc, List<Cd2exp> d2expLst) {
        m_npf = npf;
        m_loc = loc;
        m_d2expLst = d2expLst;
    }

}



