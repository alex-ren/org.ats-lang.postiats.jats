package jats.utfpl.utfpl;

public class D2Ecst implements Id2exp_node {
    public Cd2cst m_d2cst;
    
    public Cd2cst getCst() {
        return m_d2cst;
    }
    
    public D2Ecst(Cd2cst d2cst) {
        m_d2cst = d2cst;
    }

}
