package jats.utfpl.stfpl.dynexp3;

import jats.utfpl.stfpl.stype.ISType;
import jats.utfpl.stfpl.stype.NamedType;
import jats.utfpl.stfpl.stype.RecType;

import java.util.List;

public class D3Etup implements Id3exp_node {
    public int m_knd;  // 0: flat, 1: boxed
    public List<Cd3exp> m_d2es;
    private NamedType m_type;
    
    public D3Etup(int knd, List<Cd3exp> d2es, NamedType ty) {
        m_knd = knd;
        m_d2es = d2es;
        m_type = ty;
        RecType rec_type = (RecType)m_type.getContent();
        if (rec_type.getKind() != m_knd) {
            throw new Error("Check this. m_knd is " + m_knd + 
                    " while m_type.getKind() is " + rec_type.getKind());
        }
    }

    @Override
    public ISType getType() {
        return m_type;
    }


}
