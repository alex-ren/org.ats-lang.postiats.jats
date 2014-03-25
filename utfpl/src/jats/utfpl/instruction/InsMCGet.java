package jats.utfpl.instruction;

import java.util.List;

public class InsMCGet implements UtfplInstruction {
    public List<TID> m_localHolders;
    public List<TID> m_globalVars;

    public InsMCGet(List<TID> localHolders, List<TID> globalVars) {
        m_localHolders = localHolders;
        m_globalVars = globalVars;
    }
    
    @Override
    public Object accept(InsVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public Boolean hasSideEffect() {
        return true;
    }

}
