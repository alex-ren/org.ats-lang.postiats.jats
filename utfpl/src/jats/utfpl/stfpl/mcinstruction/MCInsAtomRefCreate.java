package jats.utfpl.stfpl.mcinstruction;

public class MCInsAtomRefCreate implements IMCInstruction {

    public MCSId m_holder;
    
    public MCInsAtomRefCreate(MCSId holder) {
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
