package jats.utfpl.csps;

import jats.utfpl.instruction.GlobalExtCode;

import java.util.List;

public class ProgramCSPS {
    public List<VariableInfo> m_globalVars;
    public List<CBlock> m_main;
    public List<FunctionCSPS> m_procLst;
    
    public List<GlobalExtCode> m_extCodeLst;
    
    public ProgramCSPS(List<VariableInfo> globalVars
            , List<CBlock> main
            , List<FunctionCSPS> procLst
            , List<GlobalExtCode> extCodeLst) {
        m_globalVars = globalVars;
        m_main = main;
        m_procLst = procLst;
        m_extCodeLst = extCodeLst;
    }
}
