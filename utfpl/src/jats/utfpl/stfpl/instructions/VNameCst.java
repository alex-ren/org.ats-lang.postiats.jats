package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.Cd3cst;


public class VNameCst implements IVarName {
    public Cd3cst m_cst;
    
    public VNameCst(Cd3cst cst) {
        m_cst = cst;
    }
}
