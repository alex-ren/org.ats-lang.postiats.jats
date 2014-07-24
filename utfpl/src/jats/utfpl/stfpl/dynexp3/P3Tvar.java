package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.stype.ISType;


public class P3Tvar implements Ip3at_node {
    public Cd3var m_var;
    
    public P3Tvar(Cd3var var) {
        m_var = var;
    }

    @Override
    public ISType getType() {
        return m_var.m_stype;
    }
}
