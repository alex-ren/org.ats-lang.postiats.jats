package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSType.Decorator;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.ArrayValue;

public class ArrayType implements ATSType {
    ATSType m_type;  // type of element
    int m_len;
    
    public ArrayType(ATSType type, int len) {
        m_type = type;
        m_len = len;
    }
//
//    @Override
//    public String toString() {
//        return ArrayValue.class.getSimpleName();
//    }
//    
    @Override
    public int getSize() {
        return m_len * m_type.getSize();
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


}
