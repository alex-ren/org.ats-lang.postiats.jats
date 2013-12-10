package jats.utfpl.tree;

import java.util.List;

public class ProgramTree implements TreeNode {
    public List<IDec> m_decs;
    
    public ProgramTree(List<IDec> decs) {
        m_decs = decs;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
