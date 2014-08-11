package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.Cloc_t;


import java.util.List;
import java.util.Set;

public class CSDefFun {
    public Cloc_t m_loc;
    public CSSId m_name;
    public int m_lin;  // something to do with linear type?
    public List<CSVar> m_paras;
    public List<ICSInstruction> m_inss;  // body of the function
    public Set<CSVar> m_env;
    
    public CSDefFun(Cloc_t loc, 
    	         	CSSId name, 
                     int lin,  // something to do with linear type?
                     List<CSVar> paras,
                     List<ICSInstruction> inss,  // body of the function
                     Set<CSVar> env) {
        m_loc = loc;
        m_name = name;
        m_lin = lin;
        m_paras = paras;
        m_inss = inss;
        m_env = env;
                
    }
}
