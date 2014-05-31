package jats.utfpl.utfpl.staexp;


import java.util.List;

// I guess this is the lambda expression in the statics of ATS.

public class S2Efun implements Is2exp_node {
    public int m_npf;
    public List<Cs2exp> m_arg;
    public Cs2exp m_res;
    
    public S2Efun(int npf, List<Cs2exp> arg, Cs2exp res) {
        m_npf = npf;
        m_arg = arg;
        m_res = res;
        
    }

}
