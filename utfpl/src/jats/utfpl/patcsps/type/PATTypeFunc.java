package jats.utfpl.patcsps.type;

public class PATTypeFunc implements PATType {

    private PATType m_retType;
    private Boolean m_hasEffect;
    
    public PATTypeFunc(PATType retType, boolean hasEffect) {
        m_retType = retType;
        m_hasEffect = hasEffect;
    }
    
    public PATTypeFunc(boolean hasEffect) {
        m_retType = PATTypeSingleton.cUnknownType;
        m_hasEffect = hasEffect;
    }
    
    public PATTypeFunc() {
        m_retType = PATTypeSingleton.cUnknownType;
        m_hasEffect = null;
    }
    
    public PATType getRetType() {
        return m_retType;
    }
    
    public boolean hasEffect() {
        return m_hasEffect;
    }
    
    public void updateEffect(boolean b) {
        m_hasEffect = b;
    }
}
