package jats.utfpl.tree;

public class ExpAtom extends IExp {
    public String m_text;
    
    public ExpAtom(Location loc, String text) {
        super(loc);
        m_text = text;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
