package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.staexp.Cs2exp;
import jats.utfpl.stfpl.stype.ISType;

public class D2EannType implements Id2exp_node {

    public Cd2exp m_d2exp;
    public Cs2exp m_s2exp;
    
    private ISType m_stype;
    
    public void updateType(ISType ty) {
        m_stype = ty;
    }
    
    public D2EannType(Cd2exp d2exp, Cs2exp s2exp) {
        m_d2exp = d2exp;
        m_s2exp = s2exp;
    }

    @Override
    public ISType getSType() {
        if (null == m_stype) {
            throw new Error("check this");
        } else {
            return m_stype;
        }
    }
    
    @Override
    public void normalizeType() {
        m_stype = m_stype.normalize();
    }
    

}
