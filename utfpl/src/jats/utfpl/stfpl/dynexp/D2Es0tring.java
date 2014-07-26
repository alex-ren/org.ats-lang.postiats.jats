package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.ISType;


public class D2Es0tring implements Id2exp_node {
    public String m_s0tring;
    
    private ISType m_ty;
    
    public void updateType(ISType ty) {
        m_ty = ty;
    }
    
    public D2Es0tring(String s0tring) {
        m_s0tring = s0tring;
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


