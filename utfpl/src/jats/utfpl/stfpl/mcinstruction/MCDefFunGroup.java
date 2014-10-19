package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.dynexp.Efunkind;

import java.util.List;

public class MCDefFunGroup {
    public Efunkind m_knd;
    public List<MCDefFun> m_funs;
    
    public MCDefFunGroup(Efunkind knd, List<MCDefFun> funs) {
        m_knd = knd;
        m_funs = funs;

    }
}
