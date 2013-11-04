package jats.utfpl.instruction;

public class InsLoadArray implements UtfplInstruction {
    public TID m_localHolder;
    public TID m_globalVar;
    public ValPrim m_localIndex;

    public InsLoadArray(TID globalVar, ValPrim localIndex, TID localHolder) {
        
        m_globalVar = globalVar;
        m_localIndex = localIndex;
        m_localHolder = localHolder;
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
