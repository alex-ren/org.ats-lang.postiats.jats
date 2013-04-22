package org.ats_lang.postiats.jats.type;

import java.util.Map;

public class BoxedType extends PrimType implements ATSUpdatableType {
    private ATSType m_type;
    
    private BoxedType(ATSType ty) {
        super(Decorator.TYPE);
        m_type = ty;
    }

    public void setInnerType(ATSType type) {
        m_type = type;
    }
    
    @Override
    public int getSize() {
        return PtrkType.cType.getSize();
    }
    
    public ATSType getInnerType() {
        return m_type;
    }

    public static final BoxedType cType = new BoxedType(VoidType.cType);
    
    @Override
    public void update(ATSType innerType) {
        if (m_type instanceof VoidType) {
            throw new Error("non-changable");
        } else {
            m_type = innerType;
        }
    }

    @Override
    public BoxedType createUpdatable(ATSType ty) {
        BoxedType ret = new BoxedType(ty);
        return ret;
    }

}
