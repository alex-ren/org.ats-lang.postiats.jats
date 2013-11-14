package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class PInsLoadArray implements PIns {
    public TID m_localHolder;
    public TID m_globalVar;
    public PExp m_localIndex;
    
    public PInsLoadArray(TID globalVar, PExp localIndex, TID localHolder) {
        m_globalVar = globalVar;
        m_localIndex = localIndex;
        m_localHolder = localHolder;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
