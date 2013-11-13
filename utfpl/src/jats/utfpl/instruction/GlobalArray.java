package jats.utfpl.instruction;

public class GlobalArray implements GlobalEntity {
    
    private TID m_tid;
    private int m_size;
    
    public GlobalArray(TID tid, int size) {
        m_tid = tid;
        m_size = size;
    }

//    @Override
//    public Object accept(InsVisitor visitor) {
//        return visitor.visit(this);
//    }
    
    @Override
    public TID getTID() {
        return m_tid;
    }
    
    public int getSize() {
        return m_size;
    }

}
