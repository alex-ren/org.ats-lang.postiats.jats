package jats.utfpl.stfpl.pats;

import java.util.List;

public class PInsCond extends PIns {
    public PExp m_cond;
    public List<PStat> m_true;
    public List<PStat> m_false;

    public PInsCond(PExp cond, List<PStat> trueBranch, List<PStat> falseBranch, boolean effect) {
    	super(effect);
        m_cond = cond;
        m_true = trueBranch;
        m_false = falseBranch;
        
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
