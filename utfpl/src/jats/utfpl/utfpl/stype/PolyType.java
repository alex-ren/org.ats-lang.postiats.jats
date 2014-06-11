package jats.utfpl.utfpl.stype;

import java.util.List;

public class PolyType extends BoxedType {
    public List<PolyParaType> m_paras;
    public ISType m_body;
    
    public PolyType(List<PolyParaType> paras, ISType body) {
        m_paras = paras;
        m_body = body;
    }

}
