package jats.utfpl.patcsps;

import jats.utfpl.csps.VariableInfo;

public class PGDecVar implements PGDec {
    public VariableInfo m_vi;
    public PExp m_exp;  // initial value
    
    public PGDecVar(VariableInfo vi) {
        m_vi = vi;
        m_exp = null;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
