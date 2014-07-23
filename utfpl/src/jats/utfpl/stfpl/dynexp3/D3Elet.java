package jats.utfpl.stfpl.dynexp3;


import jats.utfpl.stfpl.stype.ISType;

import java.util.List;

public class D3Elet implements Id3exp_node {

    public List<Cd3ecl> m_d3cs;
    public Cd3exp m_body;
    
    public D3Elet(List<Cd3ecl> d3cs, Cd3exp body) {
        m_d3cs = d3cs;
        m_body = body;
    }

    @Override
    public ISType getType() {
        return m_body.m_node.getType();
    }

}
