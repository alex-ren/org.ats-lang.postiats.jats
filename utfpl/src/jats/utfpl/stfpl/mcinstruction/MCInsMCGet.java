package jats.utfpl.stfpl.mcinstruction;

import java.util.List;

public class MCInsMCGet implements IMCInstruction {
    public MCSId m_localHolder;
    public List<MCSIdUser> m_globalVars;

    public MCInsMCGet(MCSId localHolder, List<MCSIdUser> globalVars) {
        m_localHolder = localHolder;
        m_globalVars = globalVars;
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
