package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PExpFormClosure implements PExp {
    public MCSId m_funLab;
    public PExp m_env;
    
    public PExpFormClosure(MCSId funLab, PExp env) {
        m_funLab = funLab;
        m_env = env;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
