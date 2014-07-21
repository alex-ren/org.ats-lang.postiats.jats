package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.ISType;


public class D2Evar implements Id2exp_node {
    public Cd2var m_d2var;

    public D2Evar(Cd2var d2var) {
        m_d2var = d2var;
    }

    @Override
    public ISType getSType() {
        return m_d2var.getSType();
    }

}
