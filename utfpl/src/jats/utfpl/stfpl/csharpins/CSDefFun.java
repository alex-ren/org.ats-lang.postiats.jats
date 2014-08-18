package jats.utfpl.stfpl.csharpins;

import jats.utfpl.stfpl.Cloc_t;
import jats.utfpl.stfpl.csharptype.CSFunType;

import java.util.List;

public class CSDefFun {
    public Cloc_t m_loc;
    public CSSId m_name;
    public int m_lin;  // something to do with linear type?
    public List<CSSId> m_paras;
    public List<ICSInstruction> m_inss;  // body of the function

    
    public CSDefFun(Cloc_t loc, 
    	         	CSSId name, 
                     int lin,  // something to do with linear type?
                     List<CSSId> paras,
                     List<ICSInstruction> inss  // body of the function
                     ) {
        m_loc = loc;
        m_name = name;
        m_lin = lin;
        m_paras = paras;
        m_inss = inss;
                
    }
    
    CSFunType getType() {
        return (CSFunType)m_name.getType();
    }
}
