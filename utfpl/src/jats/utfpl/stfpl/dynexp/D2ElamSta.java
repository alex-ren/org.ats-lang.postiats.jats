package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.staexp.Cs2exp;
import jats.utfpl.stfpl.staexp.Cs2var;
import jats.utfpl.stfpl.stype.ISType;

import java.util.List;


public class D2ElamSta implements Id2exp_node {
    public Cd2exp m_d2exp;
    public List<Cs2var> m_s2vs;  // static variables
    public List<Cs2exp> m_s2ps;  // predictes in statics
    
    private ISType m_stype;
    
    public void updateType(ISType ty) {
        m_stype = ty;
    }
    
    public D2ElamSta(List<Cs2var> s2vs, List<Cs2exp> s2ps, Cd2exp d2exp) {
        m_s2vs = s2vs;
        m_s2ps = s2ps;
        m_d2exp = d2exp;
        m_stype = null;
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
