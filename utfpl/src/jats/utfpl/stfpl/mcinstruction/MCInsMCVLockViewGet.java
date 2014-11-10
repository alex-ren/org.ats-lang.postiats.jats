package jats.utfpl.stfpl.mcinstruction;

import java.util.List;

public class MCInsMCVLockViewGet implements IMCInstruction {
    public List<IMCValPrim> m_args;
    public boolean m_isret;

    public MCInsMCVLockViewGet(List<IMCValPrim> args, boolean isret) {
        m_args = args;
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
