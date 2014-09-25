package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.Cloc_t;
import jats.utfpl.stfpl.csharptype.CSFunType;

import java.util.List;

public class MCDefFun {
    public Cloc_t m_loc;
    public MCSId m_name;
    public int m_lin;  // something to do with linear type?
    public List<MCSId> m_paras;
    public List<IMCInstruction> m_inss;  // body of the function

    
    public MCDefFun(Cloc_t loc, 
    	         	MCSId name, 
                     int lin,  // something to do with linear type?
                     List<MCSId> paras,
                     List<IMCInstruction> inss  // body of the function
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
