package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.stype.ISType;


public class P3Tany implements Ip3at_node {
    
    private ISType m_type;
    
    public P3Tany(ISType type) {
        m_type = type;
    }

    @Override
    public ISType getType() {
        return m_type;
    }

}


