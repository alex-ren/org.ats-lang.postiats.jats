package jats.utfpl.stfpl.mcinstruction;


public class MCInsMutexCreate implements IMCInstruction {
    public MCSId m_holder;
    
    public MCInsMutexCreate(MCSId holder) {
        m_holder = holder;
    }

    @Override
    public Object accept(IMCInsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return false;
    }

}
