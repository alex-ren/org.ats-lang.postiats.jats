package jats.utfpl.tree;

public class DecValBind implements IDec {
    
    public ExpId m_id;
    public IExp m_exp;

    public DecValBind(IExp id, IExp exp) {
        if (id instanceof ExpId) {
            m_id = (ExpId) id;
            
        } else if (id == ExpTuple.Void) { // val () = xxx
            m_id = new ExpId(null);
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
