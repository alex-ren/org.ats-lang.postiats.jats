package jats.utfpl.stfpl.pats;

public class PInsMCAssert extends PIns {
    
    public PExp m_localSrc;
    
    public PInsMCAssert(PExp localSrc, boolean effect) {
    	super(effect);
        m_localSrc = localSrc;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }
}
