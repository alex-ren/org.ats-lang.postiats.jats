package jats.utfpl.stfpl.mcinstruction;

import java.util.List;

public class MCInsMCGet implements IMCInstruction {
    public MCSIdFun m_localHolder;
    public List<MCSIdFun> m_globalVars;

    public MCInsMCGet(MCSIdFun localHolder, List<MCSIdFun> globalVars) {
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
