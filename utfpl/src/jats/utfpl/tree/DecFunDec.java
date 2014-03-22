package jats.utfpl.tree;

import java.util.List;

public class DecFunDec extends IDec {
    public ExpId m_id;
    public ExpId m_trueName;
    public List<ExpId> m_paralst;
    // type of function :todo: get such information from ATS compiler
    
    public DecFunDec(Location loc, ExpId id, ExpId trueName, List<ExpId> paralst) {
        super(loc);
        m_id = id;
        m_trueName = trueName;
        m_paralst = paralst;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
