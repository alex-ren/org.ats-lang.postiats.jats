package jats.utfpl.tree;

import java.util.List;

public class LetExp implements Exp {
    public List<Dec> m_decs;
    public Exp m_exp;
    
    public LetExp(List<Dec> decs, Exp exp) {
        m_decs = decs;
        m_exp = exp;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
