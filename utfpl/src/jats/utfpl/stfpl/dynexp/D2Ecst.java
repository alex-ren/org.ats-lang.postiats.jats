package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.ISType;


// This can be true, false, or function name in "implement".
public class D2Ecst implements Id2exp_node {
    public Cd2cst m_d2cst;
    
    public boolean isTrue() {
    	return m_d2cst.m_symbol.m_str.equals("true_bool");
    }
    
    public boolean isFalse() {
    	return m_d2cst.m_symbol.m_str.equals("false_bool");
    }
    
    public Cd2cst getCst() {
        return m_d2cst;
    }
    
    public D2Ecst(Cd2cst d2cst) {
        m_d2cst = d2cst;
    }

    @Override
    public ISType getSType() {
        return m_d2cst.getSType();
    }
    

}
