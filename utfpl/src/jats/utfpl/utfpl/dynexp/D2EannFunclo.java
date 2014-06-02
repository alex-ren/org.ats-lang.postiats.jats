package jats.utfpl.utfpl.dynexp;

import jats.utfpl.utfpl.staexp.Ifunclo;

/*
 * This class is used in function implementation.
 * m_d2exp would be a D2Eann_seff, which holds the function body (no parameter is included).
 * Ifunclo describes the property of function: function or a closure (many kinds of closure)
 */
public class D2EannFunclo implements Id2exp_node {
    
    public Cd2exp m_d2exp;
    public Ifunclo m_funclo;
    
    public D2EannFunclo(Cd2exp d2exp, Ifunclo funclo) {
        m_d2exp = d2exp;
        m_funclo = funclo;
    }

}
