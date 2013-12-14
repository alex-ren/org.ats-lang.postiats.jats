package jats.utfpl.tree;

public class DecValBind extends IDec {
    
    public ExpId m_id;
    public IExp m_exp;

    public DecValBind(Location loc, IExp id, IExp exp) {
        super(loc);
        if (id instanceof ExpId) {
            m_id = (ExpId) id;
        } else if (id instanceof ExpTuple && ((ExpTuple)id).isVoid()) { // val () = xxx
            m_id = new ExpId(id.getLoc(), null);
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
