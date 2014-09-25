package jats.utfpl.stfpl.mcinstruction;

import java.util.List;

public class MCInsMCSet implements IMCInstruction {
    public List<IMCValPrim> m_localValues;
    public List<MCSIdUser> m_globalVars;
    
    public MCInsMCSet(List<IMCValPrim> localValues, List<MCSIdUser> globalVars) {
        m_localValues = localValues;
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
