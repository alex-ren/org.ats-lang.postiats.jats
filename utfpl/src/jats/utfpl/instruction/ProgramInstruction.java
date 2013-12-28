package jats.utfpl.instruction;


import java.util.List;

public class ProgramInstruction {

    private List<GlobalEntity> m_gEntities;  // including both global variable and value
    private List<UtfplInstruction> m_insLst;  // including the main and the definitions of other functions.
    private List<FunctionInstruction> m_funcLst;
    
    public List<UtfplInstruction> getInsLst() {
        return m_insLst;
    }
    
    public List<GlobalEntity> getGlobalEntities() {
        return m_gEntities;
    }
    
    public List<FunctionInstruction> getFuncLst() {
        return m_funcLst;
    }
    
    public ProgramInstruction(List<GlobalEntity> gEntities, List<UtfplInstruction> insLst, List<FunctionInstruction> funcLst) {
        m_gEntities = gEntities;
        m_insLst = insLst;
        m_funcLst = funcLst;
    }
    
    public void setFunctions(List<FunctionInstruction> funcLst) {
        m_funcLst = funcLst;
    }
}
