package jats.utfpl.stfpl.pats;

public interface PNode {
    public Object accept(PNodeVisitor visitor);

}
