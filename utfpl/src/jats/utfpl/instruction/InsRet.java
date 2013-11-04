package jats.utfpl.instruction;

public class InsRet implements UtfplInstruction {
    
    public ValPrim m_v;
    
    public InsRet(ValPrim v) {
        m_v = v;
    }

    public boolean isVoid() {
        return TupleValue.cNone == m_v;
    }
    
    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean hasSideEffect() {
        return false;
    }

}

