package jats.utfpl.stfpl.mcinstruction;


public class MCInsMutexAlloc implements IMCInstruction {
    public MCSIdFun m_holder;
    
    public MCInsMutexAlloc(MCSIdFun holder) {
        m_holder = holder;
    }

    @Override
    public Object accept(IMCInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return true;
    }

}
