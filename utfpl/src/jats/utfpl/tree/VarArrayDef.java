package jats.utfpl.tree;

public class VarArrayDef implements Dec {

    public IdExp m_id;
    public int m_size;
    public Type m_ty;
    
    public VarArrayDef(IdExp id, int size, Type ty) {
        m_id = id;
        m_size = size;
        m_ty = ty;
    }
    
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
