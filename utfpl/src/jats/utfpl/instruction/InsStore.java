package jats.utfpl.instruction;

public class InsStore implements UtfplInstruction {
    public ValPrim m_localSrc;
    public TID m_globalDest;
    
    public InsStore(ValPrim localSrc, TID globalDest) {
        m_localSrc = localSrc;
        m_globalDest = globalDest;
        
    }

    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean hasSideEffect() {
        return true;
    }

}
