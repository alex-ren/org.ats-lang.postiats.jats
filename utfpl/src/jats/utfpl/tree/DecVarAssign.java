package jats.utfpl.tree;

public class DecVarAssign extends IDec {
    public ExpId m_id;
    public IExp m_exp;
    
    public DecVarAssign(Location loc, ExpId id, IExp exp) {
        super(loc);
        m_id = id;
        m_exp = exp;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
