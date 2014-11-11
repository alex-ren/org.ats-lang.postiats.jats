package jats.utfpl.stfpl.mcinstruction;

public class MCInsAtomRefGet implements IMCInstruction {

    public MCSId m_ref;
    public MCSId m_holder;
    
    public MCInsAtomRefGet(MCSId ref, MCSId holder) {
        m_ref = ref;
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
