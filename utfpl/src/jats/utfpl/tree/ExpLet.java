package jats.utfpl.tree;

import java.util.List;

public class ExpLet extends IExp {
    public List<IDec> m_decs;
    public IExp m_exp;
    
    public ExpLet(Location loc, List<IDec> decs, IExp exp) {
        super(loc);
        m_decs = decs;
        m_exp = exp;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
