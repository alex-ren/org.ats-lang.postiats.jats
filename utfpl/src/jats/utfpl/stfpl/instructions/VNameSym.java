package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.Cd3sym;
import jats.utfpl.stfpl.stype.ISType;

/*
 * I don't care much about the identity of the object.
 */
public class VNameSym implements IVarName {
    public Cd3sym m_sym;
    
 // Should be used inside factory
    public VNameSym(Cd3sym sym) {
        m_sym = sym;
    }

    @Override
    public ISType getType() {
        return m_sym.m_stype;
    }

    @Override
    public String toStringCS() {
        return m_sym.toString();
    }

    @Override
    public String toStringIns() {
        return m_sym.toString();
    }
    
	@Override
    public String toStringNoStamp() {
		throw new Error("Should not happen.");
    }

	@Override
    public String toStringWithStamp() {
	    throw new Error("Should not happen.");
    }

}
