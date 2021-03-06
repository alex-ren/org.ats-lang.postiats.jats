package jats.utfpl.stfpl.mcinstruction;

public class MCInsMCAssert implements IMCInstruction {
    public IMCValPrim m_vp;
    public boolean m_isret;
    
    public MCInsMCAssert(IMCValPrim vp, boolean isret) {
        m_vp = vp;
        m_isret = isret;
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
