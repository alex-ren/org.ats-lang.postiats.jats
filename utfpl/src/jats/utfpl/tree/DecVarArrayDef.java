package jats.utfpl.tree;

public class DecVarArrayDef extends IDec {

    public ExpId m_id;
    public int m_size;
    public EType m_ty;
    
    public DecVarArrayDef(Location loc, ExpId id, int size, EType ty) {
        super(loc);
        m_id = id;
        m_size = size;
        m_ty = ty;
    }
    
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
