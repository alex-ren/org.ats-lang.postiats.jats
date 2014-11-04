package jats.utfpl.stfpl.mcinstruction;

public class MCInsMCSet implements IMCInstruction {
    public IMCValPrim m_localv;
    public MCSId m_globalv;
    public boolean m_isret;
    
    public MCInsMCSet(IMCValPrim localv, MCSId globalv, boolean isret) {
        m_localv = localv;
        m_globalv = globalv;
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
