package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.Cloc_t;
import jats.utfpl.stfpl.dynexp3.Cp3at;

import java.util.List;

public class DefFun {
    public Cloc_t m_loc;
    public SId m_name;
    public int m_lin;  // something to do with linear type?
    public List<Cp3at> m_p3ts;
    public List<IStfplInstruction> m_inss;  // body of the function
    
    public DefFun(Cloc_t loc, 
                     SId name, 
                     int lin,  // something to do with linear type?
                     List<Cp3at> p3ts,
                     List<IStfplInstruction> inss) {
        m_loc = loc;
        m_name = name;
        m_lin = lin;
        m_p3ts = p3ts;
        m_inss = inss;                
    }
}

