package jats.utfpl.stfpl.mcinstruction;

public class MCInsMCSet implements IMCInstruction {
    public IMCValPrim m_src;
    public MCSId m_dst;
    public boolean m_isret;
    
    public MCInsMCSet(IMCValPrim src, MCSId dst, boolean isret) {
        m_src = src;
        m_dst = dst;
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
