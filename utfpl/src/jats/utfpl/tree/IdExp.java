package jats.utfpl.tree;


public class IdExp implements Exp {
    public String m_id;
    public TID m_tid;
    
    public IdExp(String id) {
        m_id = id;
        m_tid = null;
    }
    
    public TID updateTIDNew() {
//        System.out.println("updateTIDNew " + m_id);
        m_tid = TID.createUnique(m_id);
        return m_tid;
    }
    
    public void updateTID(TID tid) {
        m_tid = tid;
    }

    @Override
    public Object accept(TreeVisitor visitor) {
        return visitor.visit(this);
    }
}
