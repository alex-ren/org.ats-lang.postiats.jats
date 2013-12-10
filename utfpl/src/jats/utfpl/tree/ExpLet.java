package jats.utfpl.tree;

import java.util.List;

public class ExpLet implements IExp {
    public List<IDec> m_decs;
    public IExp m_exp;
    
    public ExpLet(List<IDec> decs, IExp exp) {
        m_decs = decs;
        m_exp = exp;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
