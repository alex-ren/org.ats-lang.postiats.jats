package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.ISType;


public class D2Ei0nt implements Id2exp_node {
    public String m_i0nt;
    
    private ISType m_ty;
    
    public void updateType(ISType ty) {
        m_ty = ty;
    }
    public D2Ei0nt(String i0nt) {
        m_i0nt = i0nt;
    }

    @Override
    public ISType getSType() {
        if (null == m_ty) {
            throw new Error("check this");
        } else {
            return m_ty;
        }
    }
    
    @Override
    public void normalizeType() {
        throw new Error("check this");
    }
    
}
