package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp.Efunkind;

import java.util.List;
import java.util.Set;

public class DefFunGroup {
    public Efunkind m_knd;
    public List<DefFun> m_funs;
    public String m_env_name;
    public Set<EscapedVar> m_env;  
    
    public DefFunGroup(Efunkind knd, List<DefFun> funs, String env_name, Set<EscapedVar> env) {
        m_knd = knd;
        m_funs = funs;
        m_env_name = env_name;
        m_env = env;
    }
}
