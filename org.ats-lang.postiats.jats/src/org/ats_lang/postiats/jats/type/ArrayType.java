package org.ats_lang.postiats.jats.type;

import java.util.Map;


public class ArrayType extends PrimType implements ATSUpdatableType {
    ATSType m_type;  // type of element
    
    private ArrayType(ATSType type) {
        super(Decorator.TYPE);
        m_type = type;
    }
//
//    @Override
//    public String toString() {
//        return ArrayValue.class.getSimpleName();
//    }
//    
    @Override
    public int getSize() {
        return PtrkType.cType.getSize();
    }
//
//    @Override
//    public ArrayValue createDefault() {
//        ATSValue [] arr = new ATSValue[m_len];
//        for (int i = 0; i < m_len; ++i) {
//            arr[i] = m_type.createDefault();
//        }
//        
//        return new ArrayValue(this, arr);
//    }
    
    public ATSType getInnerType() {
        return m_type;
    }

    public static final ArrayType cType = new ArrayType(VoidType.cType);
    
    @Override
    public void update(ATSType innerType) {
        if (m_type instanceof VoidType) {
            throw new Error("non-changable");
        } else {
            m_type = innerType;
        }
    }
    @Override
    public ArrayType createUpdatable(ATSType ty) {
        ArrayType ret = new ArrayType(ty);
        return ret;
    }

}
