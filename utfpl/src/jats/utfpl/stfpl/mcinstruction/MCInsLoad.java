package jats.utfpl.stfpl.mcinstruction;


public class MCInsLoad implements IMCInstruction {

    public MCSId m_l_holder;
    public MCSId m_g_src;

    public MCInsLoad(MCSIdFun g_src, MCSIdFun l_holder) {
        
        m_g_src = g_src;
        m_l_holder = l_holder;
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
