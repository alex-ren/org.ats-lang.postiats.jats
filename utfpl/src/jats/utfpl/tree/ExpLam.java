package jats.utfpl.tree;

import java.util.List;

public class ExpLam extends IExp {
    public List<ExpId> m_paralst;
    public IExp m_body;
    
    public ExpLam(Location loc, List<ExpId> paralst, IExp exp) {
        super(loc);
        m_paralst = paralst;
        m_body = exp;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
