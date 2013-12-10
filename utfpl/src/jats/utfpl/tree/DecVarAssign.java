package jats.utfpl.tree;

public class DecVarAssign implements IDec {
    public ExpId m_id;
    public IExp m_exp;
    
    public DecVarAssign(ExpId id, IExp exp) {
        m_id = id;
        m_exp = exp;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
