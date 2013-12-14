package jats.utfpl.tree;

public class DecVarDef extends IDec {
    public ExpId m_id;
    public IExp m_exp;
    // public Type m_ty;
    
    public DecVarDef(Location loc, ExpId id, IExp exp) {
        super(loc);
        m_id = id;
        m_exp = exp;
        // m_ty = ty;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
