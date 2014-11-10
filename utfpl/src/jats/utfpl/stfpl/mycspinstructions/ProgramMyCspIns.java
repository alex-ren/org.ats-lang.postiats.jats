package jats.utfpl.stfpl.mycspinstructions;

import jats.utfpl.instruction.GlobalExtCode;

import java.util.List;

public class ProgramMyCspIns {
    public List<VariableInfo> m_globalVars;
    public List<MyCspGroup> m_main;
    public List<FunctionMyCsp> m_procLst;
    
    public List<GlobalExtCode> m_extCodeLst;
    
    public ProgramMyCspIns(List<VariableInfo> globalVars
            , List<MyCspGroup> main
            , List<FunctionMyCsp> procLst
            , List<GlobalExtCode> extCodeLst) {
        m_globalVars = globalVars;
        m_main = main;
        m_procLst = procLst;
        m_extCodeLst = extCodeLst;
    }
}
