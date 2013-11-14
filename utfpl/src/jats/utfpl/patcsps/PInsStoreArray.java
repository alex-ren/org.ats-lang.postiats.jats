package jats.utfpl.patcsps;

import jats.utfpl.instruction.TID;

public class PInsStoreArray implements PIns {
    public TID m_globalVar;
    public PExp m_localIndex;
    public PExp m_localSrc;
    
    public PInsStoreArray(PExp localSrc, TID globalVar, PExp localIndex) {
        m_localSrc = localSrc;
        m_globalVar = globalVar;
        m_localIndex = localIndex;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
