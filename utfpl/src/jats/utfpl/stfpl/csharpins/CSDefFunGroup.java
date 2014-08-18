package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.csharptype.CSTBookingEnv;
import jats.utfpl.stfpl.dynexp.Efunkind;

import java.util.List;

public class CSDefFunGroup {
    public Efunkind m_knd;
    public List<CSDefFun> m_funs;
    public CSTBookingEnv m_env_book;
    
    public CSDefFunGroup(Efunkind knd, List<CSDefFun> funs, CSTBookingEnv env_book) {
        m_knd = knd;
        m_funs = funs;
        m_env_book = env_book;
    }
}
