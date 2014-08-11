package jats.utfpl.stfpl.csharpins;

import java.util.List;

public class CSInsTuple implements ICSInstruction {
    public List<ICSValPrim> m_elements;
    public CSSId m_holder;
    
    public CSInsTuple(List<ICSValPrim> elements, CSSId holder) {
        m_elements = elements;
        m_holder = holder;
    }
}
