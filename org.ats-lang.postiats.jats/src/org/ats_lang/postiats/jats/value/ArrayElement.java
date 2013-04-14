package org.ats_lang.postiats.jats.value;

public class ArrayElement implements LValue {
    Object [] m_arr;
    int m_index;
    
    @Override
    public Object getValue() {
        return m_arr[m_index];
    }

    @Override
    public void updateValue(Object v) {
        if (v instanceof LValue) {
            m_arr[m_index] = ((LValue)v).getValue();
        } else {
            m_arr[m_index] = v;
        }
        
    }
}
