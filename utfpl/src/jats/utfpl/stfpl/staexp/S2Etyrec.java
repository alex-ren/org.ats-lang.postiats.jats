package jats.utfpl.stfpl.staexp;

import java.util.List;

public class S2Etyrec implements Is2exp_node {
    public Ityreckind m_knd;
    public int m_npf;
    public List<Clabs2exp> m_ls2es;
    
    public S2Etyrec(Ityreckind knd, int npf, List<Clabs2exp> ls2es) {
        m_knd = knd;
        m_npf = npf;
        m_ls2es = ls2es;
    }
    

}
