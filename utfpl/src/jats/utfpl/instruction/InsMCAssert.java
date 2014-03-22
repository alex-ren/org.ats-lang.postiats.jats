package jats.utfpl.instruction;

public class InsMCAssert implements UtfplInstruction {
    public ValPrim m_localSrc;
    
    public InsMCAssert(ValPrim localSrc) {
        m_localSrc = localSrc;
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
