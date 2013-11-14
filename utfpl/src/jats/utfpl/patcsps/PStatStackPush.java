package jats.utfpl.patcsps;

public class PStatStackPush implements PStat {
    public PExpID m_exp;
    
    public PStatStackPush(PExpID exp) {
        m_exp = exp;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
