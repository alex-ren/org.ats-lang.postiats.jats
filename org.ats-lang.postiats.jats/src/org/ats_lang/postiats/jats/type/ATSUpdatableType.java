package org.ats_lang.postiats.jats.type;

public abstract class ATSUpdatableType extends ATSPrimType {
    private ATSType m_type;
    
    protected ATSUpdatableType(ATSType type) {
        super(Decorator.TYPE);
        m_type = type;
    }
    // create an updatable one and insert it into scope
    public abstract ATSType createUpdatable(ATSType ty);
    
    public ATSType getInnerType() {
        return m_type;
    }
    
    public void update(ATSType innerType) {
        if (m_type instanceof VoidType) {
            throw new Error("non-changable");
        } else {
            m_type = innerType;
        }
    }

}
