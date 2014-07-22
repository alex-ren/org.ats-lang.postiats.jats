package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.ISType;


public class D2Eexp implements Id2exp_node {
    public Cd2exp m_d2exp;
    
    public D2Eexp(Cd2exp d2exp) {
        m_d2exp = d2exp;
    }

    @Override
    public ISType getSType() {
        return m_d2exp.d2exp_node.getSType();
    }

}
