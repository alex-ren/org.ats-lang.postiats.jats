package jats.utfpl.tree;

import java.util.List;

public class Program implements UtfplTreeNode {
    public List<Dec> m_decs;
    
    public Program(List<Dec> decs) {
        m_decs = decs;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
