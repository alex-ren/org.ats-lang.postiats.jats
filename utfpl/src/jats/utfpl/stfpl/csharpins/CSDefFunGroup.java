package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.dynexp.Efunkind;

import java.util.List;
import java.util.Set;

public class CSDefFunGroup {
    public Efunkind m_knd;
    public List<CSDefFun> m_funs;
    
    public CSSId m_name;  // name of the closure
    public Set<CSSIdUser> m_env;
    
    public CSDefFunGroup(Efunkind knd, List<CSDefFun> funs, CSSId name, Set<CSSIdUser> env) {
        m_knd = knd;
        m_funs = funs;
        
        m_name = name;
        m_env = env;
        
//        for (CSSIdUser var : env) {
//            System.out.println("ssssssssss var is " + var.toStringCS());
//        }
    }
}
