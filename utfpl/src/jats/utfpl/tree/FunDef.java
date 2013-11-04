package jats.utfpl.tree;

import java.util.List;

public class FunDef implements Dec {
    public IdExp m_id;
    public List<IdExp> m_paralst;
    public Exp m_body;
    // type of function :todo: get such information from ATS compiler
    
    public FunDef(IdExp id, List<IdExp> paralst, Exp exp) {
        m_id = id;
        m_paralst = paralst;
        m_body = exp;
    }
    
    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
