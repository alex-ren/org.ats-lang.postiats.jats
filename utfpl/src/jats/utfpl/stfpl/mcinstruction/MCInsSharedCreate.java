package jats.utfpl.stfpl.mcinstruction;

public class MCInsSharedCreate implements IMCInstruction {

    public MCSId m_holder;
    public IMCValPrim m_vp;
    
    public MCInsSharedCreate(MCSId holder, IMCValPrim vp) {
        m_holder = holder;
        m_vp = vp;
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
