package jats.utfpl.instruction;


import java.util.List;

public class ProgramInstruction {

    private List<GlobalEntity> m_gEntities;  // including both global variable and value
    private List<UtfplInstruction> m_insLst;  // including the main and the definitions of other functions.
                         // but after Pass3 in InstructionClosureConverter, function definitions are removed
                         // from the body of other function definition.
    private List<FunctionInstruction> m_funcLst;
    
    private List<GlobalExtCode> m_extCodeLst;
    
    public List<UtfplInstruction> getInsLst() {
        return m_insLst;
    }
    
    public List<GlobalEntity> getGlobalEntities() {
        return m_gEntities;
    }
    
    public List<FunctionInstruction> getFuncLst() {
        return m_funcLst;
    }
    
    public List<GlobalExtCode> getExtCodeLst() {
        return m_extCodeLst;
    }
    
    
    
    public ProgramInstruction(List<GlobalEntity> gEntities, 
            List<UtfplInstruction> insLst, 
            List<FunctionInstruction> funcLst,
            List<GlobalExtCode> extCodeLst) {
        m_gEntities = gEntities;
        m_insLst = insLst;
        m_funcLst = funcLst;
        m_extCodeLst = extCodeLst;
    }
    
    public void setFunctions(List<FunctionInstruction> funcLst) {
        m_funcLst = funcLst;
    }
}
