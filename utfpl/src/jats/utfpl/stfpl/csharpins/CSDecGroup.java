package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.instructions.Edeckind;

import java.util.List;

public class CSDecGroup {
    public Edeckind m_knd;
    public List<CSSId> m_names;
    
    public CSDecGroup(Edeckind knd, List<CSSId> names) {
        m_knd = knd;
        m_names = names;
    }
}
