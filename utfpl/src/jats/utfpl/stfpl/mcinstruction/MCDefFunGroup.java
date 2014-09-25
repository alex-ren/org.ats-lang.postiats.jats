package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.dynexp.Efunkind;

import java.util.List;
import java.util.Set;

public class MCDefFunGroup {
    public Efunkind m_knd;
    public List<MCDefFun> m_funs;
    
    public MCSId m_name;  // name of the closure
    public Set<MCSIdUser> m_env;
    
    public MCDefFunGroup(Efunkind knd, List<MCDefFun> funs, MCSId name, Set<MCSIdUser> env) {
        m_knd = knd;
        m_funs = funs;
        
        m_name = name;
        m_env = env;
    }
}
