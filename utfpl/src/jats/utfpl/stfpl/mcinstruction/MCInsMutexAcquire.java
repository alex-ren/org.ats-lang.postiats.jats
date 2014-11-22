package jats.utfpl.stfpl.mcinstruction;

public class MCInsMutexAcquire implements IMCInstruction {

    public MCSId m_mutex;
    public boolean m_isret;
    
    public MCInsMutexAcquire(MCSId mutex, boolean isret) {
        m_mutex = mutex;
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
