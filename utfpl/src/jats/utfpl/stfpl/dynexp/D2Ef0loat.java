package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.ISType;


public class D2Ef0loat implements Id2exp_node {
    public String m_f0loat;
    
    private ISType m_ty;
    
    public D2Ef0loat(String f0loat) {
        m_f0loat = f0loat;
    }
    
    public void updateType(ISType ty) {
        m_ty = ty;
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
