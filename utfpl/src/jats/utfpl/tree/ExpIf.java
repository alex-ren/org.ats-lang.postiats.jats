package jats.utfpl.tree;

public class ExpIf extends IExp {
    public IExp m_cond;
    public IExp m_btrue;
    public IExp m_bfalse;
    
    public ExpIf(Location loc, IExp cond, IExp btrue, IExp bfalse) {
        super(loc);
        m_cond = cond;
        m_btrue = btrue;
        m_bfalse = bfalse;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
