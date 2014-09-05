package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.RecType;

import java.util.List;

public class D3Etup implements Id3exp_node {
    public int m_knd;  // 0: flat, 1: boxed
    public List<Cd3exp> m_d3es;
    private RecType m_type;
    
    public D3Etup(int knd, List<Cd3exp> d3es, RecType ty) {
        m_knd = knd;
        m_d3es = d3es;
        m_type = ty;
        if (m_type.getKind() != m_knd) {
            throw new Error("Check this. m_knd is " + m_knd + 
                    " while m_type.getKind() is " + m_type.getKind());
        }
    }

    @Override
    public ISType getType() {
        return m_type;
    }


}
