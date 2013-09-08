package jats.utfpl.patcsps;

public interface PNode {
    Object accept(PNodeVisitor visitor);

}
