package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp.Efunkind;
import java.util.List;
import java.util.Set;

public class DefFunGroup implements IFunDef {
    public Efunkind m_knd;
    public List<DefFun> m_funs;
    public SId m_env_name;  // can be null if not closure
    public Set<SIdUser> m_env;  // can be null if not closure
    
    public DefFunGroup(Efunkind knd, List<DefFun> funs, SId env_name, Set<SIdUser> env) {
        m_knd = knd;
        m_funs = funs;
        m_env_name = env_name;
        m_env = env;
        
//        for (SIdUser var: env) {
//            System.out.println("ssssssssss var is " + var.toStringCS());
//        }
    }
}
