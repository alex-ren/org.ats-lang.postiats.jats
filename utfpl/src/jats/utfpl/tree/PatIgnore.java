package jats.utfpl.tree;

public class PatIgnore extends IPat {

    public PatIgnore(Location loc) {
        super(loc);
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
