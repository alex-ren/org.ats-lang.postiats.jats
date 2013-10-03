package jats.utfpl.patcsps;

public class PExpStackPush implements PExp {
    public PExpID m_exp;
    
    public PExpStackPush(PExpID exp) {
        m_exp = exp;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
