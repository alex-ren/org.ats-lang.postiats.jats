package jats.utfpl.tree;

public class ValDef implements Dec {
    public IdExp m_id;
    public Exp m_exp;

    public ValDef(Exp id, Exp exp) {
        if (id instanceof IdExp) {
            m_id = (IdExp) id;
            // val _ = xxx
            if (m_id.m_sid.equals("_")) {
                m_id = null;
            }
            // val () = xxx
        } else if (id == TupleExp.Void) {
            m_id = null;
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
