package jats.utfpl.stfpl.instructions;


import java.util.List;

public class InsDecGroup {
    public Edeckind m_knd;
    public List<IVarName> m_names;
    
    public InsDecGroup(Edeckind knd, List<IVarName> names) {
        m_knd = knd;
        m_names = names;
    }
}
