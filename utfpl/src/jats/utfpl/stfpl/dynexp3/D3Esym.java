package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.stype.ISType;


public class D3Esym implements Id3exp_node {
    // Some example, "+", "-", "*", ">", and etc.
    public Cd3sym m_d3sym;
    
    public D3Esym(Cd3sym d3sym) {
        m_d3sym = d3sym;
    }

    @Override
    public ISType getType() {
        return m_d3sym.m_stype;
    }

}
