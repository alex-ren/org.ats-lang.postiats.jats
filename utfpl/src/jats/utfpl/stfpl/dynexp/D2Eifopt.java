package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.ISType;


public class D2Eifopt implements Id2exp_node {
    public Cd2exp m_test;
    public Cd2exp m_then;
    public Cd2exp m_else;  // may be null
    
    private ISType m_ty;
    
    public void updateType(ISType ty) {
        m_ty = ty;
    }
    
    public D2Eifopt(Cd2exp _test, Cd2exp _then, Cd2exp _else) {
        m_test = _test;
        m_then = _then;
        m_else = _else;
    }
    
    @Override
    public ISType getSType() {
        if (null == m_ty) {
            throw new Error("check this");
        } else {
            return m_ty;
        }
    }
    
}
