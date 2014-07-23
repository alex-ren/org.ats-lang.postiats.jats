package jats.utfpl.stfpl.instructions;


import java.util.List;

public class DecGroup {
    public Edeckind m_knd;
    public List<IVarName> m_names;
    
    public DecGroup(Edeckind knd, List<IVarName> names) {
        m_knd = knd;
        m_names = names;
    }
}
