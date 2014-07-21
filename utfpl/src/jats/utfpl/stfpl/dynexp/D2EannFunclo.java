package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.staexp.Ifunclo;
import jats.utfpl.stfpl.stype.ISType;

/*
 * This class is used in function implementation and function definition. And it is enclosed in D2Elam_dyn.
 * m_d2exp would be a D2Eann_seff, which holds the function body (no parameter is included).
 * Ifunclo describes the property of function: function or a closure (many kinds of closure)
 */
public class D2EannFunclo implements Id2exp_node {
    
    public Cd2exp m_d2exp;
    public Ifunclo m_funclo;  // This is supposed to indicate whether it's a function
                              // or a closure. But such information may be incorrect at all.
    
    private ISType m_stype;
    
    public D2EannFunclo(Cd2exp d2exp, Ifunclo funclo) {
        m_d2exp = d2exp;
        m_funclo = funclo;
    }

    @Override
    public ISType getSType() {
        return m_stype.normalize();
    }

}
