package jats.utfpl.tree;

public class DecExtCode extends IDec {
    
    public String m_content;

    public DecExtCode(Location loc, String content) {
        super(loc);
        m_content = content;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
