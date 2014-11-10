package jats.utfpl.stfpl.mcinstruction;


public class MCInsMCGet implements IMCInstruction {
    public MCSId m_holder;
    public MCSId m_src;

    public MCInsMCGet(MCSId holder, MCSId src) {
        m_holder = holder;
        m_src = src;
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
