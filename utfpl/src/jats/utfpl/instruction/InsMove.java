package jats.utfpl.instruction;


public class InsMove implements UtfplInstruction {
    public TID m_holder;
    public ValPrim m_vp;
    
    public InsMove(ValPrim vp, TID holder) {
        m_holder = holder;
        m_vp = vp;
    }

    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return false;
    }

}
