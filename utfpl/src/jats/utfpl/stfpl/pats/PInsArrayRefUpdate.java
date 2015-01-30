package jats.utfpl.stfpl.pats;


public class PInsArrayRefUpdate extends PIns {
    
    public PExp m_ref;
    public PExp m_pos;
    public PExp m_v;
    
    public PInsArrayRefUpdate(PExp ref, PExp pos, PExp v, boolean effect) {
    	super(effect);
        m_ref = ref;
        m_pos = pos;
        m_v = v;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
