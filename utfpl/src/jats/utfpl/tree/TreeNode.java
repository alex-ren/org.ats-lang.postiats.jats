package jats.utfpl.tree;

public abstract class TreeNode {
    private Location m_loc;
    
    protected TreeNode(Location loc) {
        m_loc = loc;
    }
    
    abstract public Object accept(TreeVisitor visitor);
    
    Location getLoc() {
        return m_loc;
    }
    
    
}
