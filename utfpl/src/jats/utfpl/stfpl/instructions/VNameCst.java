package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.stype.ISType;


public class VNameCst implements IVarName {
    public Cd3cst m_cst;
    
    public VNameCst(Cd3cst cst) {
        m_cst = cst;
    }

    @Override
    public ISType getSType() {
        return m_cst.m_stype;
    }
}
