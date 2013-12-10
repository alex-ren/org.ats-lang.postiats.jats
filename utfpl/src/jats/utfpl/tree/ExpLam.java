package jats.utfpl.tree;

import java.util.List;

public class ExpLam implements IExp {
    public List<ExpId> m_paralst;
    public IExp m_body;
    
    public ExpLam(List<ExpId> paralst, IExp exp) {
        m_paralst = paralst;
        m_body = exp;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
