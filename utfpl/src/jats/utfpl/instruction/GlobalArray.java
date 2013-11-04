package jats.utfpl.instruction;

public class GlobalArray implements GlobalEntity {
    
    public TID m_tid;
    public int m_size;
    
    public GlobalArray(TID tid, int size) {
        m_tid = tid;
        m_size = size;
    }

    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

}
