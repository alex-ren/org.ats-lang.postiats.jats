package jats.utfpl.stfpl.mcinstruction;


public class MCInsTIdAllocate implements IMCInstruction {
    public MCSId m_holder;
    
    public MCInsTIdAllocate(MCSId holder) {
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
