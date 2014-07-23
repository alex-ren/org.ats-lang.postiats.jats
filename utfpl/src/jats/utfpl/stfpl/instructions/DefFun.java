package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.Cloc_t;
import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.dynexp3.Cp3at;
import jats.utfpl.stfpl.staexp.Ifunclo;

import java.util.List;
import java.util.Set;

public class DefFun {
    public Cloc_t m_loc;
    public SId m_name;
    public int m_lin;  // something to do with linear type?
    public List<Cp3at> m_p3ts;
    public Ifunclo m_funclo;
    public List<IStfplInstruction> m_inss;  // body of the function
    public Set<Cd3var> m_env;
    
    public DefFun(Cloc_t loc, 
                     SId name, 
                     int lin,  // something to do with linear type?
                     List<Cp3at> p3ts,
                     Ifunclo funclo,
                     List<IStfplInstruction> inss,  // body of the function
                     Set<Cd3var> env) {
        m_loc = loc;
        m_name = name;
        m_lin = lin;
        m_p3ts = p3ts;
        m_funclo = funclo;
        m_inss = inss;
        m_env = env;
                
    }
}

