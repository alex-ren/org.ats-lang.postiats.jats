package jats.utfpl.tree;

public class IfExp implements Exp {
    public Exp m_cond;
    public Exp m_btrue;
    public Exp m_bfalse;
    
    public IfExp(Exp cond, Exp btrue, Exp bfalse) {
        m_cond = cond;
        m_btrue = btrue;
        m_bfalse = bfalse;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
