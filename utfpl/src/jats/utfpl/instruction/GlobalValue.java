package jats.utfpl.instruction;

public class GlobalValue implements GlobalEntity {
    public TID m_tid;
    
    public GlobalValue(TID tid) {
        m_tid = tid;
    }
    

    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }
}
