package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.instructions.Edeckind;

import java.util.List;

public class MCDecAtomValGroup {
    public Edeckind m_knd;
    public List<MCSId> m_names;  // Declaration of all global values. Include
                                // extern val x: int
                                // implement x = 3 + 4
                                // val x = 4 + 5
    
    public MCDecAtomValGroup(Edeckind knd, List<MCSId> names) {
        m_knd = knd;
        m_names = names;
    }
}
