package jats.utfpl.tree;

public class ExpAtom implements IExp {
    public String m_text;
    
    public ExpAtom(String text) {
        m_text = text;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
