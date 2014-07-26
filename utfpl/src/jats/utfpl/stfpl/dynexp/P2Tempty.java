package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.ISType;


public class P2Tempty implements Ip2at_node {

    private ISType m_type;
    
    public P2Tempty() {
        m_type = null;
    }
    
    public P2Tempty(ISType type) {
        m_type = type;
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

    @Override
    public void normalizeType() {
    }
}
