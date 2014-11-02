package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.Cd3cst;
import jats.utfpl.stfpl.stype.ISType;


public class VNameCst implements IVarName {
    public Cd3cst m_cst;

    // Should be used inside factory
    public VNameCst(Cd3cst cst) {
        m_cst = cst;
    }

    @Override
    public ISType getType() {
        return m_cst.m_stype;
    }

    @Override
    public String toStringCS() {
        return m_cst.toString();
    }

    @Override
    public String toStringIns() {
        return m_cst.toString();
    }

	@Override
    public String toStringNoStamp() {
	    return m_cst.toStringNoStamp();
    }
}
