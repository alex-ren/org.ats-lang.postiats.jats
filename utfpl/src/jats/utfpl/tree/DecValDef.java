package jats.utfpl.tree;

import jats.utfpl.instruction.TID;

// initialization of global value
public class DecValDef implements IDec {
    public ExpId m_id;
    public IExp m_exp;

    public DecValDef(IExp id, IExp exp) {
        if (id instanceof ExpId) {
            m_id = (ExpId) id;
            
        } else if (id == ExpTuple.Void) { // val () = xxx
            m_id = new ExpId(null);
        } else {
            throw new Error("not supported");
        }

        m_exp = exp;
    }
    
    public boolean hasName() {
        return m_id.m_tid != TID.ANONY;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }

}
