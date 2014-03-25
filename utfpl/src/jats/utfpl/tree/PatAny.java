package jats.utfpl.tree;

public class PatAny extends IPat {

    protected PatAny(Location loc) {
        super(loc);
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
