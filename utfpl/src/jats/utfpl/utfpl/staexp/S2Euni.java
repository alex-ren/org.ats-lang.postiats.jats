package jats.utfpl.utfpl.staexp;


import java.util.List;

public class S2Euni implements Is2exp_node {
    public List<Cs2var> m_s2vs;
    public List<Is2exp_node> m_s2ps;
    public Is2exp_node m_s2e_body;
    
    public S2Euni(List<Cs2var> s2vs, List<Is2exp_node> s2ps, Is2exp_node s2e_body) {
        m_s2vs = s2vs;
        m_s2ps = s2ps;
        m_s2e_body = s2e_body;        
    }

}
