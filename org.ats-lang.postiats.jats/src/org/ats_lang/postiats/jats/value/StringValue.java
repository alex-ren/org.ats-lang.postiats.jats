package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.StringType;

// null terminated char array
public class StringValue implements ATSValue {

    public static final StringType m_type = StringType.cType;

    private CharValue[] m_arr;
    
    public StringValue(String v) {
        char [] arr = v.toCharArray();
        m_arr = new CharValue[arr.length + 1];
        for (int i = 0; i < m_arr.length - 1; ++i) {
            m_arr[i] = new CharValue(arr[i]);
        }
        m_arr[m_arr.length - 1] = new CharValue('\0');
        
    }
    
    public StringValue(CharValue[] arr) {
        m_arr = arr;
    }
    
    public static StringValue create(String v) {
        return new StringValue(v);
    }
    
    public PtrValue toPtrValue() {
        return new PtrValue(m_arr);
    }
    
    @Override
    public void copyfrom(ATSValue v) {
        if (v instanceof StringValue) {
            m_arr = ((StringValue) v).m_arr;
        } else {
            throw new Error("StringValue::copyfrom: copy from non-string value.");
        }
    }
    
    @Override
    public StringValue deepcopy() {
        return new StringValue(m_arr);
    }

    @Override
    public String getContent() {
        int len = 0;
        len = m_arr.length - 1;
        char [] arr = new char[len];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = m_arr[i].getContent();
        }
        return new String(arr);
    }

    @Override
    public StringType getType() {
        return m_type;
    }
    
    
}
