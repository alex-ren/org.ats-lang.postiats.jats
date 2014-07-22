package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.ISType;

/*
 * The return type and return value of a function definition.
 */
public class D2EannSeff implements Id2exp_node {
    public Cd2exp m_d2exp;
    // public Is2eff_node;
    
    public D2EannSeff(Cd2exp d2exp) {
        m_d2exp = d2exp;
    }

    @Override
    public ISType getSType() {
        return m_d2exp.d2exp_node.getSType();
    }


}
