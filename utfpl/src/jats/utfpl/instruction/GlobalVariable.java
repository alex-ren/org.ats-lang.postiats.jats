package jats.utfpl.instruction;

public class GlobalVariable implements GlobalEntity {
    
    private TID m_tid;
    
    public GlobalVariable(TID tid) {
        m_tid = tid;
    }
    
//
//    @Override
//    public Object accept(InsVisitor visitor) {
//        return visitor.visit(this);
//    }

    @Override
    public TID getTID() {
        return m_tid;
    }
}
