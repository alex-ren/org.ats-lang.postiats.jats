package jats.utfpl.stfpl.pats;

public class PInsMCAssert implements PIns {
    
    public PExp m_localSrc;
    
    public PInsMCAssert(PExp localSrc) {
        m_localSrc = localSrc;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }
}
