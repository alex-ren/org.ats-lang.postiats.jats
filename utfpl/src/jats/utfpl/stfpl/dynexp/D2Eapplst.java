package jats.utfpl.stfpl.dynexp;


import jats.utfpl.stfpl.stype.ISType;

import java.util.List;

public class D2Eapplst implements Id2exp_node {
    public Cd2exp m_d2e_fun;
    
    private ISType m_ty;
    
    // m_d2as_arg can have multiple elements. E.g. val xx = foof {int}{int} (pf1 | 0, 1)(pf2 | 33.3)
    // m_d2e_fun is foof
    // m_d2as_arg has 4 elements, D2EXPARGsta, D2EXPARGsta, D2EXPARGdyn, D2EXPARGdyn
    // Read the code $PATSHOME/MEDIUM/UTFPL0/evaluating/eval_d2exp.dats: aux_d2exp_applst
    public List<Id2exparg> m_d2as_arg;
    
    public D2Eapplst(Cd2exp d2e_fun, List<Id2exparg> d2as_arg) {
        m_d2e_fun = d2e_fun;
        m_d2as_arg = d2as_arg;
    }
    
    public void updateSType(ISType ty) {
        m_ty = ty;
    }

    @Override
    public ISType getSType() {
        if (null == m_ty) {
            throw new Error("check this");
        } else {
            return m_ty;
        }
    }
    
    

}
