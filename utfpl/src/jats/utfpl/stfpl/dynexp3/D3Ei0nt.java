package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.stype.ISType;


public class D3Ei0nt implements Id3exp_node {
    public String m_i0nt;
    public ISType m_ty;
    
    public D3Ei0nt(String i0nt, ISType ty) {
        m_i0nt = i0nt;
        m_ty = ty;
    }

    @Override
    public ISType getType() {
        return m_ty;
    }

}
