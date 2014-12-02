package jats.utfpl.stfpl.mcinstruction;


public class MCInsMCVLockViewPut implements IMCInstruction {

	public MCSId m_v;
    public boolean m_isret;

    public MCInsMCVLockViewPut(MCSId v, boolean isret) {
        m_v = v;
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
