package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.Cloc_t;
import jats.utfpl.stfpl.stype.ISType;
//import jats.utfpl.stfpl.instructions.SId;

import java.util.List;
//import java.util.Map;

public class MCDefFun {
    public Cloc_t m_loc;
    public MCSId m_name;
    public int m_lin;  // something to do with linear type?
    public List<MCSId> m_paras;

    public List<IMCInstruction> m_inss;  // body of the function
    
//    // Caution: The following two members may become invalid after the
//    // processing of "if" branch, since a closure can now be formed in
//    // multiple places along different branches.
//    
//    public Map<SId, MCSId> m_env_map;  // new name for the values stored in env
    
//    private Aux.Address m_addr;
//    private Boolean m_has_effect;

//    public MCSId m_env_name;  // name of the environment as the extra parameter
//                              // This one can be null.
    
    public MCDefFun(Cloc_t loc, 
                    MCSId name, 
                    int lin,  // something to do with linear type?
                    List<MCSId> paras,
//                    MCSId env_name,  
//                    Map<SId, MCSId> env_map,
                    List<IMCInstruction> inss  // body of the function
                     ) {
        m_loc = loc;
        m_name = name;
        m_lin = lin;
        m_paras = paras;
//        m_env_name = env_name;
//        m_env_map = env_map;
        m_inss = inss;
                
    }
    
    ISType getType() {
        return m_name.getType();
    }
}
