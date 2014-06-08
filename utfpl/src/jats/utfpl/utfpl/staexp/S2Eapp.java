package jats.utfpl.utfpl.staexp;


import java.util.List;

public class S2Eapp implements Is2exp_node {
    public Cs2exp m_fun;  // Seems that this is S2Ecst, e.g. list0_t0ype_type
    public List<Cs2exp> m_arglst;
    
    public S2Eapp(Cs2exp fun, List<Cs2exp> arglst) {
        m_fun = fun;
        m_arglst = arglst;
    }
}
