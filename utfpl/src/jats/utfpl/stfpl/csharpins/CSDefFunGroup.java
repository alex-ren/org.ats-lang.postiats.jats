package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.dynexp.Efunkind;

import java.util.List;

public class CSDefFunGroup {
    public Efunkind m_knd;
    public List<CSDefFun> m_funs;
    
    public CSDefFunGroup(Efunkind knd, List<CSDefFun> funs) {
        m_knd = knd;
        m_funs = funs;
    }
}
