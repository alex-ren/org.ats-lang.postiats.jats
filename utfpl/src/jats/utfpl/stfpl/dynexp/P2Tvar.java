package jats.utfpl.stfpl.dynexp;


public class P2Tvar implements Ip2at_node {
    public Cd2var m_var;
    
    public P2Tvar(Cd2var var) {
        m_var = var;
    }

    @Override
    public void normalizeType() {
        m_var.normalizeType();
    }
}
