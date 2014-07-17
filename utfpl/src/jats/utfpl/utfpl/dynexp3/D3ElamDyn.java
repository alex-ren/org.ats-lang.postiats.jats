package jats.utfpl.utfpl.dynexp3;


import jats.utfpl.utfpl.staexp.Ifunclo;

import java.util.List;

public class D3ElamDyn implements Id3exp_node {
    public int m_lin;  // something to do with linear type?
    public List<Cp3at> m_p3ts;  // arguments including proof and normal arguments
                                // proof arguments appear first in the list
    // These Cp3at's are actually P3Tann's.
    
    public Cd3exp m_d3exp;  // body of the function
    
    public Ifunclo m_funclo;

    public D3ElamDyn(int lin, List<Cp3at> p3ts, Cd3exp d3exp, Ifunclo funclo) {
        m_lin = lin;
        m_p3ts = p3ts;
        m_d3exp = d3exp;
        m_funclo = funclo;
        
    }

}
