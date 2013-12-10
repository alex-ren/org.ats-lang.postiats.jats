package jats.utfpl.tree;

import java.util.List;

public class DecFunDef implements IDec {
    public ExpId m_id;
    public List<ExpId> m_paralst;
    public IExp m_body;
    // type of function :todo: get such information from ATS compiler
    
    public DecFunDef(ExpId id, List<ExpId> paralst, IExp exp) {
        m_id = id;
        m_paralst = paralst;
        m_body = exp;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
