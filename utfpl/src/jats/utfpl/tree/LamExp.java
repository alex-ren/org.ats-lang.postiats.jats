package jats.utfpl.tree;

import java.util.List;

public class LamExp implements Exp {
    public List<IdExp> m_paralst;
    public Exp m_body;
    
    public LamExp(List<IdExp> paralst, Exp exp) {
        m_paralst = paralst;
        m_body = exp;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
