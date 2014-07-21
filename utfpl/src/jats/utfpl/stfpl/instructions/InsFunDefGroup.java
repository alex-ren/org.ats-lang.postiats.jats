package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp.Efunkind;

import java.util.List;

public class InsFunDefGroup {
    public Efunkind m_knd;
    public List<InsFunDef> m_funs;
    
    public InsFunDefGroup(Efunkind knd, List<InsFunDef> funs) {
        m_knd = knd;
        m_funs = funs;
    }
}
