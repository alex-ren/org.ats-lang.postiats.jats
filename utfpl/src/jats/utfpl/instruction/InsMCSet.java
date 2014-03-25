package jats.utfpl.instruction;

import java.util.List;

public class InsMCSet implements UtfplInstruction {
    public List<ValPrim> m_localValues;
    public List<TID> m_globalVars;
    
    public InsMCSet(List<ValPrim> localValues, List<TID> globalVars) {
        m_localValues = localValues;
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
