package org.ats_lang.postiats.jats.value;

public class ObjElement implements LValue {
    
    private Object m_value;

    @Override
    public Object getValue() {
        return m_value;
    }

    @Override
    public void updateValue(Object v) {
        if (v instanceof LValue) {
            m_value = ((LValue)v).getValue();
        } else {
            m_value = v;
        }
        
    }
}
