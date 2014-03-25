package jats.utfpl.tree;

public class PatEmpty extends IPat {
    protected PatEmpty(Location loc) {
        super(loc);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
