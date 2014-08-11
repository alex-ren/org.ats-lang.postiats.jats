package jats.utfpl.stfpl.instructions;


import java.util.List;

public class DecGroup {
    public Edeckind m_knd;
    public List<SId> m_names;
    
    public DecGroup(Edeckind knd, List<SId> names) {
        m_knd = knd;
        m_names = names;
    }
}
