package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.ISType;


public class P2Tany implements Ip2at_node {
    private ISType m_type;
    
    public P2Tany() {
        m_type = null;
    
    }
    
    public void updateSType(ISType type) {
        m_type = type;
    }
    
    public ISType getSType() {
        if (null == m_type) {
            throw new Error("Check this.");
        } else {
            return m_type;
        }
    }
}


