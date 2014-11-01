package jats.utfpl.stfpl.instructions;

import java.util.List;

public class DecAtomValGroup {
    public Edeckind m_knd;
    public List<SId> m_names;  // Declaration of all global values. Include
                                // extern val x: int
                                // implement x = 3 + 4
                                // val x = 4 + 5
    
    public DecAtomValGroup(Edeckind knd, List<SId> names) {
        m_knd = knd;
        m_names = names;
    }
}
