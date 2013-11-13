package jats.utfpl.instruction;

public class InsStoreArray implements UtfplInstruction {
    
    public ValPrim m_localValue;
    public TID m_globalVar;
    public ValPrim m_localIndex;

    public InsStoreArray(ValPrim localValue, TID globalVar, ValPrim localIndex) {
        m_localValue = localValue;
        m_globalVar = globalVar;
        m_localIndex = localIndex;
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