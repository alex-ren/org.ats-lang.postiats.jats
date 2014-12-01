package jats.utfpl.stfpl.mcinstruction;

public class MCInsArrayRefCreate implements IMCInstruction {

    public MCSId m_holder;
    public IMCValPrim m_len;
    public IMCValPrim m_vp;
    
    public MCInsArrayRefCreate(MCSId holder, IMCValPrim len, IMCValPrim vp) {
        m_holder = holder;
        m_len = len;
        m_vp = vp;
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
