package jats.utfpl.instruction;

public class GlobalVariable implements GlobalEntity {
    
    public TID m_tid;
    
    public GlobalVariable(TID tid) {
        m_tid = tid;
    }
    

    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

}
