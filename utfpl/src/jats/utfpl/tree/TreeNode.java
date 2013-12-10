package jats.utfpl.tree;

public interface TreeNode {
    Object accept(TreeVisitor visitor);

}
