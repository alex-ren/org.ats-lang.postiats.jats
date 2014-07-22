package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.Cd3var;
import jats.utfpl.stfpl.stype.ISType;


public class VNameVar implements IVarName {
    public Cd3var m_var;
    
    public VNameVar(Cd3var var) {
        m_var = var;
    }

    @Override
    public ISType getSType() {
        return m_var.m_stype;
    }
    
}
