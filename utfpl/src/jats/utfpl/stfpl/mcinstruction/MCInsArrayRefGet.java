package jats.utfpl.stfpl.mcinstruction;

public class MCInsArrayRefGet implements IMCInstruction {

    public MCSId m_ref;
    public IMCValPrim m_pos;
    public MCSId m_holder;
    
    
    public MCInsArrayRefGet(MCSId ref, IMCValPrim pos, MCSId holder) {
        m_ref = ref;
        m_pos = pos;
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
