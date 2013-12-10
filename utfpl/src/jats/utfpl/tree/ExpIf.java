package jats.utfpl.tree;

public class ExpIf implements IExp {
    public IExp m_cond;
    public IExp m_btrue;
    public IExp m_bfalse;
    
    public ExpIf(IExp cond, IExp btrue, IExp bfalse) {
        m_cond = cond;
        m_btrue = btrue;
        m_bfalse = bfalse;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
