package jats.utfpl.tree;

public class VarDef implements Dec {
    public IdExp m_id;
    public Exp m_exp;
    
    public VarDef(IdExp id, Exp exp) {
        m_id = id;
        m_exp = exp;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
