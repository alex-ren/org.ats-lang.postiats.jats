package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.stype.ISType;


public class VNameVar implements IVarName {
    public Cd3var m_var;
    
    // Should be used inside factory
    public VNameVar(Cd3var var) {
        m_var = var;
    }

    @Override
    public ISType getType() {
        return m_var.m_stype;
    }

    @Override
    public String toStringCS() {
        return m_var.toString();
    }

    @Override
    public String toStringIns() {
        return m_var.toString();
    }
    
	@Override
    public String toStringNoStamp() {
		return m_var.toString();
    }
    
}
