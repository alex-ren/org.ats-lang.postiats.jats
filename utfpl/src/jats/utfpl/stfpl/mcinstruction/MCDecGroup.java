package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.instructions.Edeckind;

import java.util.List;

public class MCDecGroup {
    public Edeckind m_knd;
    public List<MCSIdFun> m_names;
    
    public MCDecGroup(Edeckind knd, List<MCSIdFun> names) {
        m_knd = knd;
        m_names = names;
    }
}
