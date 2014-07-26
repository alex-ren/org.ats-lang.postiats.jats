package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.RecType;

import java.util.List;

public class D2Etup implements Id2exp_node {
    public int m_knd;  // 0: flat, 1: boxed
    public int m_npf;  // no. of proof, be -1 is none
    public List<Cd2exp> m_d2es;
    
    private RecType m_stype;
    
    public void updateType(RecType ty) {
        m_stype = ty;
    }
    
    public D2Etup(int knd, int npf, List<Cd2exp> d2es) {
        m_knd = knd;
        m_npf = npf;
        m_d2es = d2es;
    }
    
    @Override
    public RecType getSType() {
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
