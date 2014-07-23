package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.stype.ISType;


public class D3Es0tring implements Id3exp_node {
    public String m_s0tring;
    public ISType m_ty;
    
    public D3Es0tring(String s0tring, ISType ty) {
        m_s0tring = s0tring;
        m_ty = ty;
    }

    @Override
    public ISType getType() {
        return m_ty;
    }

}
