package jats.utfpl.utfpl.dynexp3;

import jats.utfpl.utfpl.staexp.Ifunclo;

/*
 * This class is used in function implementation and function definition. And it is enclosed in D2Elam_dyn.
 * m_d2exp would be a D2Eann_seff, which holds the function body (no parameter is included).
 * Ifunclo describes the property of function: function or a closure (many kinds of closure)
 */
public class D2EannFunclo implements Id3exp_node {
    
    public Cd3exp m_d2exp;
    public Ifunclo m_funclo;  // This is supposed to indicate whether it's a function
                              // or a closure. But such information may be incorrect at all.
    
    public D2EannFunclo(Cd3exp d2exp, Ifunclo funclo) {
        m_d2exp = d2exp;
        m_funclo = funclo;
    }

}
