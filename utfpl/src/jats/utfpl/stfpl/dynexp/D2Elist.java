package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.RecType;

import java.util.List;

/*
 * 1. usage:
 *   let
 *     xxx
 *   in
 *     (pf | v)  // This is D2Elist
 *   end
 *   ============
 *   let
 *     xxx
 *   in
 *     @(pf | v)  // This is D2Etup
 *   end
 *   ============
 *   let
 *     xxx
 *   in
 *     '(pf | v)  // This is D2Etup
 *   end
 *   
 */
public class D2Elist implements Id2exp_node {
    public int m_npf;
    public List<Cd2exp> m_d2es;
    
    private RecType m_stype;
    
    
    public D2Elist(int npf, List<Cd2exp> d2es) {
        m_npf = npf;
        m_d2es = d2es;
        m_stype = null;
    }
    
    public void updateType(RecType ty) {
        m_stype = ty;
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
