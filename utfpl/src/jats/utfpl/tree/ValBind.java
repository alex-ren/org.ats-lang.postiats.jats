package jats.utfpl.tree;

public class ValBind implements Dec {
    
    public IdExp m_id;
    public Exp m_exp;

    public ValBind(Exp id, Exp exp) {
        if (id instanceof IdExp) {
            m_id = (IdExp) id;
            
        } else if (id == TupleExp.Void) { // val () = xxx
            m_id = new IdExp(null);
        } else {
            throw new Error("not supported");
        }

        m_exp = exp;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
