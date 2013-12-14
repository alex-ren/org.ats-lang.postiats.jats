package jats.utfpl.tree;

import java.util.List;

public class DecFunDef extends IDec {
    public ExpId m_id;
    public List<ExpId> m_paralst;
    public IExp m_body;
    // type of function :todo: get such information from ATS compiler
    
    public DecFunDef(Location loc, ExpId id, List<ExpId> paralst, IExp exp) {
        super(loc);
        m_id = id;
        m_paralst = paralst;
        m_body = exp;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
