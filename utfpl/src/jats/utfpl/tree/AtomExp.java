package jats.utfpl.tree;

public class AtomExp implements Exp {
    public String m_text;
    
    public AtomExp(String text) {
        m_text = text;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
