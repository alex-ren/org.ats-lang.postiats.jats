package jats.utfpl.stfpl.mcinstruction;

public class MCInsAtomRefUpdate implements IMCInstruction {

    public MCSId m_ref;
    public IMCValPrim m_vp;
    public boolean m_isret;
    
    public MCInsAtomRefUpdate(MCSId ref, IMCValPrim vp, boolean isret) {
        m_ref = ref;
        m_vp =vp;
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
