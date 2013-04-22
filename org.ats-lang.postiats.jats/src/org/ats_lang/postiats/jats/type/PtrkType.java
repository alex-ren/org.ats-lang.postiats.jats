package org.ats_lang.postiats.jats.type;

import java.util.Map;

public class PtrkType extends PrimType implements ATSUpdatableType {

    private ATSType m_type;
    
    public static final int m_size = 4;
    

    public static final PtrkType cType = new PtrkType(VoidType.cType);
//    public static final PtrkType cType0 = new PtrkType(Decorator.T0YPE);
    
    
//    @Override
//    public String toString() {
//        return CCompTypedefs.class.getSimpleName() + "." + CCompTypedefs.CPtrValue.class.getSimpleName();
//    }
    
    @Override
    public int getSize() {
        return m_size;
    }

//    @Override
//    public PtrValue createDefault() {
//        return new PtrValue(SingletonValue.VOID);
//    }
    

    private PtrkType(ATSType ty) {
        super(Decorator.TYPE);
        m_type = ty;
    }

    @Override
    public void update(ATSType innerType) {
        if (m_type instanceof VoidType) {
            throw new Error("non-changable");
        } else {
            m_type = innerType;
        }
    }

    @Override
    public ATSType getInnerType() {
        return m_type;
    }

    @Override
    public PtrkType createUpdatable(ATSType ty) {
        PtrkType ret = new PtrkType(null);
        return ret;
    }

}
