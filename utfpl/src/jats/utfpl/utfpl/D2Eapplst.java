package jats.utfpl.utfpl;

import java.util.List;

public class D2Eapplst implements Id2exp_node {
    public Cd2exp m_d2e_fun;
    public List<Id2exparg> m_d2as_arg;
    
    public D2Eapplst(Cd2exp d2e_fun, List<Id2exparg> d2as_arg) {
        m_d2e_fun = d2e_fun;
        m_d2as_arg = d2as_arg;
    }

}
