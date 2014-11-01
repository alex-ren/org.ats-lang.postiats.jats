package jats.utfpl.stfpl.mcinstruction;


import java.util.List;

public class ProgramMCIns {

    public List<MCDecAtomValGroup> m_global_v;
    public List<MCDecFunGroup> m_decs;
    public List<MCDefFunGroup> m_defs;

    public List<MCGlobalExtCode> m_exts;
    
    public List<IMCInstruction> m_main_inss;  // global instructions

    public String m_main_name;
    
    public ProgramMCIns(List<MCDecAtomValGroup> global_v, 
            List<MCDecFunGroup> decs,
            List<MCDefFunGroup> defs,
            List<MCGlobalExtCode> exts,
            List<IMCInstruction> main_inss,
            String main_name) {
        m_global_v = global_v;
        m_decs = decs;
        m_defs = defs;
        m_exts = exts;
        m_main_inss = main_inss;
        m_main_name = main_name;
        
        
    }
    
}

