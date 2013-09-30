package jats.utfpl.patcsps;


public class PStatReturn implements PStat {

    public PExp m_v;
    
    public PStatReturn(PExp v) {
        m_v = v;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
