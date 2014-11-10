package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.D3Cextcode;

import java.util.List;

public class ProgramIns {
    public List<DecAtomValGroup> m_gvs;
    public List<DecFunGroup> m_decs;
    public List<IFunDef> m_defs;
    public List<D3Cextcode> m_exts;
    public List<IStfplInstruction> m_main_inss;
    
    public ProgramIns(
            List<DecAtomValGroup> gvs
           , List<DecFunGroup> decs
           , List<IFunDef> defs
           , List<D3Cextcode> exts
           , List<IStfplInstruction> main_inss
           ) {
        m_gvs = gvs;
        m_decs = decs;
        m_defs = defs;
        m_exts = exts;
        m_main_inss = main_inss;
        
    }
}
