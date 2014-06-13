package jats.utfpl.utfpl.stype;

import java.util.List;

public class PolyType extends BoxedType {
    public List<PolyParaType> m_paras;
    public ISType m_body;
    
    public PolyType(List<PolyParaType> paras, ISType body) {
        m_paras = paras;
        m_body = body;
    }

    @Override
    public PolyType normalize() {
        m_body = m_body.normalize();
        return this;
    }


    @Override
    public ISType instantiate(PolyParaType para, ISType arg) {
        if (m_paras.contains(para)) {
            throw new Error("This is not allowed.");
        }
        ISType body = m_body.instantiate(para, arg);
        
        return new PolyType(m_paras, body);
    }
    

}
