package jats.utfpl.stfpl.dynexp3;


import jats.utfpl.stfpl.stype.ISType;

import java.util.List;

public class D3Eapplst implements Id3exp_node {
    public Cd3exp m_fun;
    
    // m_d2as_arg can have multiple elements. E.g. val xx = foof {int}{int} (pf1 | 0, 1)(pf2 | 33.3)
    // m_d2e_fun is foof
    // m_d2as_arg has 4 elements, D2EXPARGsta, D2EXPARGsta, D2EXPARGdyn, D2EXPARGdyn
    // Read the code $PATSHOME/MEDIUM/UTFPL0/evaluating/eval_d2exp.dats: aux_d2exp_applst
    public List<D3EXPARGdyn> m_args;
    
    public List<ISType> m_inner_stype;  // Contains all the types after applying inputs one by one.
    
    public D3Eapplst(Cd3exp fun, List<D3EXPARGdyn> args, List<ISType> inner_stype) {
        m_fun = fun;
        m_args = args;
        m_inner_stype = inner_stype;
    }

    @Override
    public ISType getType() {
        return m_inner_stype.get(m_inner_stype.size() - 1);
    }

}
