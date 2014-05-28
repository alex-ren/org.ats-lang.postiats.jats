package jats.utfpl.utfpl.staexp;


import java.util.List;

public class S2Eapp implements Is2exp_node {
    public Is2exp_node m_fun;
    public List<Is2exp_node> m_arglst;
    
    public S2Eapp(Is2exp_node fun, List<Is2exp_node> arglst) {
        m_fun = fun;
        m_arglst = arglst;
    }
}
