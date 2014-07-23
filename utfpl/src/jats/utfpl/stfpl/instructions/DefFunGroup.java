package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp.Efunkind;

import java.util.List;

public class DefFunGroup {
    public Efunkind m_knd;
    public List<DefFun> m_funs;
    
    public DefFunGroup(Efunkind knd, List<DefFun> funs) {
        m_knd = knd;
        m_funs = funs;
    }
}
