package jats.utfpl.stfpl.mcinstruction;

import jats.utfpl.stfpl.csharptype.ICSTypeBooking;

import java.util.List;
import java.util.Set;

public class ProgramMCIns {
    public List<IMCGlobalEntity> m_g_eneities;  // global variable / value
    
    public List<MCGlobalExtCode> m_exts;
    public List<MCDecGroup> m_decs;  // function declaration, definition, lambda 
    
    public List<MCDefFunGroup> m_defs;  // function definition, implementation, lambda
    public List<IMCInstruction> m_main_inss;  // global instructions
    public Set<ICSTypeBooking> m_track;  // type booking info
    public String m_main_name;
    
    public ProgramMCIns(List<IMCGlobalEntity> g_eneities, 
            List<MCGlobalExtCode> exts,
            List<MCDecGroup> decs,
            List<MCDefFunGroup> defs,
            List<IMCInstruction> main_inss,
            Set<ICSTypeBooking> track,
            String main_name) {
        m_g_eneities = g_eneities;
        m_exts = exts;
        m_decs = decs;
        m_defs = defs;
        m_main_inss = main_inss;
        m_track = track;
        m_main_name = main_name;
        
        
    }
    
}

