package jats.utfpl.instruction;


import java.util.List;

public class ProgramIns {

    private List<TID> m_globalVars;
    private List<UtfplInstruction> m_insLst;
    
    public List<UtfplInstruction> getInsLst() {
        return m_insLst;
    }
    
    public List<TID> getGlobalVars() {
        return m_globalVars;
    }
    
    public ProgramIns(List<TID> globalVars, List<UtfplInstruction> insLst) {
        m_globalVars = globalVars;
        m_insLst = insLst;
    }
    
}
