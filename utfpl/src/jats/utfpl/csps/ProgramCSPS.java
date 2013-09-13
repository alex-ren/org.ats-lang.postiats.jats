package jats.utfpl.csps;

import java.util.List;

public class ProgramCSPS {
    public List<VariableInfo> m_globalVars;
    public List<CBlock> m_main;
    public List<CIProcessDef> m_procLst;
    
    public ProgramCSPS(List<VariableInfo> globalVars
            , List<CBlock> main
            , List<CIProcessDef> procLst) {
        m_globalVars = globalVars;
        m_main = main;
        m_procLst = procLst;
    }
    
    public Object accept(CSPSVisitor visitor) {
        return visitor.visit(this);
    }

}
