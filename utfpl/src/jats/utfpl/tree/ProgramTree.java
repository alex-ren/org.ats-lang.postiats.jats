package jats.utfpl.tree;

import java.util.List;

public class ProgramTree extends TreeNode {
    public List<IDec> m_decs;
    
    public ProgramTree(Location loc, List<IDec> decs) {
        super(loc);
        m_decs = decs;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
