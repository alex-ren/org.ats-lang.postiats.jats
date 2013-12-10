package jats.utfpl.tree;

public class DecVarDef implements IDec {
    public ExpId m_id;
    public IExp m_exp;
    // public Type m_ty;
    
    public DecVarDef(ExpId id, IExp exp) {
        m_id = id;
        m_exp = exp;
        // m_ty = ty;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
