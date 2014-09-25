package jats.utfpl.stfpl.mcinstruction;

public class MCInsStore implements IMCInstruction {
    public IMCValPrim m_localSrc;
    public MCSId m_globalDest;
    
    public MCInsStore(IMCValPrim localSrc, MCSId globalDest) {
        m_localSrc = localSrc;
        m_globalDest = globalDest;
        
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
