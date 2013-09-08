package jats.utfpl.csps;

import java.util.List;

import jats.utfpl.instruction.TID;

public class ProgramCSPS {
    public List<TID> m_globalVars;
    public List<CBlock> m_main;
    public List<CIProcessDef> m_procLst;
    
    public ProgramCSPS(List<TID> globalVars
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
