package jats.utfpl.patcsps;

public interface PNode {
    public Object accept(PNodeVisitor visitor);

}
