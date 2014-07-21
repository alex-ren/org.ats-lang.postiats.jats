package jats.utfpl.stfpl.staexp;

import java.util.List;

public class S2Eexi implements Is2exp_node {
    public List<Cs2var> m_s2vs;
    public List<Cs2exp> m_s2ps;
    public Cs2exp m_s2e_body;
    
    public S2Eexi(List<Cs2var> s2vs, List<Cs2exp> s2ps, Cs2exp s2e_body) {
        m_s2vs = s2vs;
        m_s2ps = s2ps;
        m_s2e_body = s2e_body;        
    }

}
