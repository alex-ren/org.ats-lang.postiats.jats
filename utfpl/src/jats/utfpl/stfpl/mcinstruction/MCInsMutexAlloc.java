package jats.utfpl.stfpl.mcinstruction;


public class MCInsMutexAlloc implements IMCInstruction {
    public MCSId m_holder;
    
    public MCInsMutexAlloc(MCSId holder) {
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
