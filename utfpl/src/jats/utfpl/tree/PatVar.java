package jats.utfpl.tree;

public class PatVar extends IPat {

    public String m_sid;
    
    public PatVar(Location loc, String sid) {
        super(loc);
        m_sid = sid;
        
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
