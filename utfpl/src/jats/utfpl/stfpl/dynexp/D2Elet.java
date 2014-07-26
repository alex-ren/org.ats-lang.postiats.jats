package jats.utfpl.stfpl.dynexp;


import jats.utfpl.stfpl.stype.ISType;

import java.util.List;

public class D2Elet implements Id2exp_node {

    public List<Cd2ecl> m_d2cs;
    public Cd2exp m_d2e_body;
    private ISType m_stype;
    
    public D2Elet(List<Cd2ecl> d2cs, Cd2exp d2e_body) {
        m_d2cs = d2cs;
        m_d2e_body = d2e_body;
    }
    
    public void updateType(ISType ty) {
        m_stype = ty;
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
