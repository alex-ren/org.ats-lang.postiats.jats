package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.Cloc_t;
import java.util.List;

public class DefFun {
    public Cloc_t m_loc;
    public SId m_name;  // function name
    public int m_lin;  // something to do with linear type?
    public List<SId> m_paras;
    public List<IStfplInstruction> m_inss;  // body of the function
    
    public DefFun(Cloc_t loc, 
                     SId name, 
                     int lin,  // something to do with linear type?
                     List<SId> paras,
                     List<IStfplInstruction> inss) {
        m_loc = loc;
        m_name = name;
        m_lin = lin;
        m_paras = paras;
        m_inss = inss;                
    }
}

