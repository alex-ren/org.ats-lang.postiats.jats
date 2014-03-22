package jats.utfpl.utfpl;

import java.util.List;

public class D2Eapplst implements Id2exp_node {
    public Cd2exp m_d2e_fun;
    
    // Since D2EXPARGdyn, which implements Id2exparg, contains a list inside,
    // m_d2as_arg is actually a list of only one element.
    // m_d2as_arg may be 2. E.g. val xx = foof {int}{int} (0, 1)
    // Not clear about the inner mechanism. Seems to be
    // one list for static argument, one list for dynamic argument.
    public List<Id2exparg> m_d2as_arg;
    
    public D2Eapplst(Cd2exp d2e_fun, List<Id2exparg> d2as_arg) {
        m_d2e_fun = d2e_fun;
        m_d2as_arg = d2as_arg;
        
        if (m_d2as_arg.size() > 1) {
            
            // throw new Error("strange, watch this, size is " + m_d2as_arg.size());
        }
    }

}
