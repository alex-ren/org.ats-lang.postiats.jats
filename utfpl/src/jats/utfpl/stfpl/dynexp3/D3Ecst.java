package jats.utfpl.stfpl.dynexp3;


// This can be true, false, or function name in "implement".
public class D3Ecst implements Id3exp_node {
    public Cd3cst m_d3cst;
    
    public boolean isTrue() {
    	return m_d3cst.m_symbol.m_str.equals("true_bool");
    }
    
    public boolean isFalse() {
    	return m_d3cst.m_symbol.m_str.equals("false_bool");
    }
    
    public D3Ecst(Cd3cst d3cst) {
        m_d3cst = d3cst;
    }

}
