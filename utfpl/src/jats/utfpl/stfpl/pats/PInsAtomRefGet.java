package jats.utfpl.stfpl.pats;

import jats.utfpl.stfpl.mcinstruction.MCSId;

public class PInsAtomRefGet implements PIns {
    public MCSId m_localHolder;
    public PExp m_globalVar;
    
    public PInsAtomRefGet(PExp globalVar, MCSId localHolder) {
        m_localHolder = localHolder;
        m_globalVar = globalVar;
    }
    
    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
