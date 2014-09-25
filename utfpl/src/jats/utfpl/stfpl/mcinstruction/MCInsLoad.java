package jats.utfpl.stfpl.mcinstruction;


public class MCInsLoad implements IMCInstruction {

    public MCSId m_localHolder;
    public MCSIdUser m_globalVar;

    public MCInsLoad(MCSIdUser globalVar, MCSId localHolder) {
        
        m_globalVar = globalVar;
        m_localHolder = localHolder;
    }

    @Override
    public Boolean hasSideEffect() {
        return true;
    }

    @Override
    public Object accept(IMCInsVisitor visitor) {
        return visitor.visit(this);
    }

}
