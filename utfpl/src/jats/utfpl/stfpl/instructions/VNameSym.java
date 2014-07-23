package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.Cd3sym;
import jats.utfpl.stfpl.stype.ISType;

public class VNameSym implements IVarName {
    public Cd3sym m_sym;
    
    public VNameSym(Cd3sym sym) {
        m_sym = sym;
    }

    @Override
    public ISType getType() {
        return m_sym.m_stype;
    }

}
