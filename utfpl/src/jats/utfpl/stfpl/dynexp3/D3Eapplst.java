package jats.utfpl.stfpl.dynexp3;


import java.util.List;

public class D3Eapplst implements Id3exp_node {
    public Cd3exp m_fun;
    
    // m_d2as_arg can have multiple elements. E.g. val xx = foof {int}{int} (pf1 | 0, 1)(pf2 | 33.3)
    // m_d2e_fun is foof
    // m_d2as_arg has 4 elements, D2EXPARGsta, D2EXPARGsta, D2EXPARGdyn, D2EXPARGdyn
    // Read the code $PATSHOME/MEDIUM/UTFPL0/evaluating/eval_d2exp.dats: aux_d2exp_applst
    public List<D3EXPARGdyn> m_args;
    
    public D3Eapplst(Cd3exp fun, List<D3EXPARGdyn> args) {
        m_fun = fun;
        m_args = args;
    }

}