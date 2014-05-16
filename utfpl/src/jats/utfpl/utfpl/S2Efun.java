package jats.utfpl.utfpl;

import java.util.List;

// I guess this is the lambda expression in the statics of ATS.

public class S2Efun implements Is2exp_node {
    public int m_npf;
    public List<Is2exp_node> m_arg;
    public Is2exp_node m_res;
    
    public S2Efun(int npf, List<Is2exp_node> arg, Is2exp_node res) {
        m_npf = npf;
        m_arg = arg;
        m_res = res;
        
    }

}
