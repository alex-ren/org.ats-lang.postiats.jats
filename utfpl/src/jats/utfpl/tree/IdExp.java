package jats.utfpl.tree;

import jats.utfpl.instruction.TID;


public class IdExp implements Exp {
    public String m_sid;
    public TID m_tid;
    
    public IdExp(String id) {
        m_sid = id;
        m_tid = null;
    }
    
    public void updateTID(TID tid) {
        m_tid = tid;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
