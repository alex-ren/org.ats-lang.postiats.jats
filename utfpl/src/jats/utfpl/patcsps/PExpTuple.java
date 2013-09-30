package jats.utfpl.patcsps;

public class PExpTuple implements PExp {
    
    public static final PExpTuple cNone = new PExpTuple();

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
