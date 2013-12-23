package jats.utfpl.instruction;


public class InsMutexAlloc implements UtfplInstruction {
    public TID m_holder;
    
    public InsMutexAlloc(TID holder) {
        m_holder = holder;
    }

    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return true;
    }

}
