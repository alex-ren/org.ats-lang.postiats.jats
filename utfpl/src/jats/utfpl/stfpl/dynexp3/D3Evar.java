package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.stype.ISType;


public class D3Evar implements Id3exp_node {
    public Cd3var m_d3var;
    
    public D3Evar(Cd3var d3var) {
        m_d3var = d3var;
    }

    @Override
    public ISType getType() {
        return m_d3var.m_stype;
    }

}
