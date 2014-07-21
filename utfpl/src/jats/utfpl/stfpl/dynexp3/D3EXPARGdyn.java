package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.Cloc_t;

import java.util.List;

public class D3EXPARGdyn {
    public Cloc_t m_loc;
    public List<Cd3exp> m_d3expLst;  // List of arguments.
    
    public D3EXPARGdyn(Cloc_t loc, List<Cd3exp> d3expLst) {
        m_loc = loc;
        m_d3expLst = d3expLst;
    }

}



