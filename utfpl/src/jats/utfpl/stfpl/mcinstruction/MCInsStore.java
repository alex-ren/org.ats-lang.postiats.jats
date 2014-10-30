package jats.utfpl.stfpl.mcinstruction;

public class MCInsStore implements IMCInstruction {
    public IMCValPrim m_l_src;
    public MCSIdFun m_g_dst;
    
    public MCInsStore(IMCValPrim l_src, MCSIdFun g_dst) {
        m_l_src = l_src;
        m_g_dst = g_dst;
        
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
