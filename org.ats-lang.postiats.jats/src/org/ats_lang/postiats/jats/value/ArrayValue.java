package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ArrayType;

public class ArrayValue implements ATSValue {
    ATSValue [] m_arr;
    ArrayType m_type;
    
    public ArrayValue(ArrayType type, ATSValue [] arr) {
        m_type = type;
        m_arr = arr;
    }
    
    public ATSValue get(int n) {
        return m_arr[n];
    }

    @Override
    public void copyfrom(ATSValue v) {
        if (v instanceof ArrayValue) {
            ATSValue [] src_arr = ((ArrayValue)v).m_arr;
            int len = src_arr.length;
            for (int i = 0; i < len; ++i) {
                m_arr[i].copyfrom(src_arr[i]);                
            }
        } else {
            throw new Error("ArrayValue copy from non-array");
        }
    }

    @Override
    public ATSValue [] getContent() {
        return m_arr;
    }
    
    @Override
    public ArrayValue deepcopy() {
        ATSValue [] arr = new ATSValue[m_arr.length];
        for (int i = 0; i < m_arr.length; ++i) {
            arr[i] = m_arr[i].deepcopy();
        }
        
        return new ArrayValue(m_type, arr);
    }

    @Override
    public ArrayType getType() {
        return m_type;
    }

}
