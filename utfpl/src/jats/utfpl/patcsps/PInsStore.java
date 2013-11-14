package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class PInsStore implements PIns {
    
    public TID m_globalVar;
    public PExp m_localSrc;
    
    public PInsStore(PExp localSrc, TID globalVar) {
        m_localSrc = localSrc;
        m_globalVar = globalVar;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
