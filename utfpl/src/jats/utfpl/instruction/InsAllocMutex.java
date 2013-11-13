package jats.utfpl.instruction;


public class InsAllocMutex implements UtfplInstruction {
    public TID m_holder;
    
    public InsAllocMutex(TID holder) {
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
