package jats.utfpl.stfpl.mcinstruction;

public class MCInsSharedCreate implements IMCInstruction {

    public MCSId m_holder;
    public IMCValPrim m_vp;
    public IMCValPrim m_n;
    
    public MCInsSharedCreate(MCSId holder, IMCValPrim vp, IMCValPrim n) {
        m_holder = holder;
        m_vp = vp;
        m_n = n;
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
