package jats.utfpl.stfpl.pats;


public class PInsAtomRefUpdate implements PIns {
    
    public PExp m_globalVar;
    public PExp m_localSrc;
    
    public PInsAtomRefUpdate(PExp localSrc, PExp globalVar) {
        m_localSrc = localSrc;
        m_globalVar = globalVar;
    }

    @Override
    public Object accept(PNodeVisitor visitor) {
        return visitor.visit(this);
    }

}
