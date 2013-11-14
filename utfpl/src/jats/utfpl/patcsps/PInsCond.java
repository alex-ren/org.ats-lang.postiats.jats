package jats.utfpl.patcsps;

import java.util.List;

public class PInsCond implements PIns {
    public PExp m_cond;
    public List<PStat> m_true;
    public List<PStat> m_false;

    public PInsCond(PExp cond, List<PStat> trueBranch, List<PStat> falseBranch) {
        m_cond = cond;
        m_true = trueBranch;
        m_false = falseBranch;
        
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
