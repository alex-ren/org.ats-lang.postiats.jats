package jats.utfpl.utfpl.dynexp3;


// This can be true, false, or function name in "implement".
public class D2Ecst implements Id3exp_node {
    public Cd3cst m_d2cst;
    
    public boolean isTrue() {
    	return m_d2cst.m_symbol.m_str.equals("true_bool");
    }
    
    public boolean isFalse() {
    	return m_d2cst.m_symbol.m_str.equals("false_bool");
    }
    
    public Cd3cst getCst() {
        return m_d2cst;
    }
    
    public D2Ecst(Cd3cst d2cst) {
        m_d2cst = d2cst;
    }

}
