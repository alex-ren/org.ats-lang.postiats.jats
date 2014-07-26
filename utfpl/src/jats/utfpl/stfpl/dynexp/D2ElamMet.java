package jats.utfpl.stfpl.dynexp;

import jats.utfpl.stfpl.stype.ISType;


/*
 * prfun qq2 {x:int} .<>.(x: propy (3)): int = 3
 */

public class D2ElamMet implements Id2exp_node {
    public Cd2exp m_d2exp;
    
    public D2ElamMet(Cd2exp d2exp) {
        m_d2exp = d2exp;
    }

    @Override
    public ISType getSType() {
        return m_d2exp.d2exp_node.getSType();
    }
    
    @Override
    public void normalizeType() {
        throw new Error("check this");
    }

}
