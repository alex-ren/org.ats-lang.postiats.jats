package jats.utfpl.stfpl.mcinstruction;

public class MCInsMCAssert implements IMCInstruction {
    public IMCValPrim m_localSrc;
    
    public MCInsMCAssert(IMCValPrim localSrc) {
        m_localSrc = localSrc;
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
