package jats.utfpl.stfpl.mcinstruction;

import java.util.List;

public class MCInsMCVLockViewGet implements IMCInstruction {
    public List<IMCValPrim> m_args;
    public MCSId m_holder;

    public MCInsMCVLockViewGet(List<IMCValPrim> args, MCSId holder) {
        m_args = args;
        m_holder = holder;
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
