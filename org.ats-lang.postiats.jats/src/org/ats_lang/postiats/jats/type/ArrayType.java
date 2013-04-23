package org.ats_lang.postiats.jats.type;

import java.util.Map;


public class ArrayType extends ATSUpdatableType {
    
    private ArrayType(ATSType type) {
        super(type);
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
    
    public static final ArrayType cType = new ArrayType(VoidType.cType);
    

    @Override
    public ArrayType createUpdatable(ATSType ty) {
        ArrayType ret = new ArrayType(ty);
        return ret;
    }

}
