package jats.utfpl.instruction;


public class InsLoad implements UtfplInstruction {

    public TID m_localHolder;
    public TID m_globalVar;

    public InsLoad(TID globalVar, TID localHolder) {
        
        m_globalVar = globalVar;
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
