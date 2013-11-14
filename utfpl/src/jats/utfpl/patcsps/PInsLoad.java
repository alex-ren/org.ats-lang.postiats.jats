package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class PInsLoad implements PIns {
    public TID m_localHolder;
    public TID m_globalVar;
    
    public PInsLoad(TID globalVar, TID localHolder) {
        m_localHolder = localHolder;
        m_globalVar = globalVar;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
