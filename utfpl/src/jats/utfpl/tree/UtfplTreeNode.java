package jats.utfpl.tree;

public interface UtfplTreeNode {
    Object accept(TreeVisitor visitor);

}
