package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.Cloc_t;
import java.util.List;
import java.util.Set;

public class ImplFun implements IFunDef {
    public Cloc_t m_loc;
    public SId m_name;  // function name
    public int m_lin;  // something to do with linear type?
    public List<SId> m_paras;
    public List<IStfplInstruction> m_inss;  // body of the function
    
    public SId m_env_name;
    public Set<SIdUser> m_env;  
    
    public ImplFun(Cloc_t loc, 
                     SId name, 
                     int lin,  // something to do with linear type?
                     List<SId> paras,
                     List<IStfplInstruction> inss,
                     SId env_name,
                     Set<SIdUser> env) {
        m_loc = loc;
        m_name = name;
        m_lin = lin;
        m_paras = paras;
        m_inss = inss;      
        
        m_env_name = env_name;
        m_env = env;
    }
}
