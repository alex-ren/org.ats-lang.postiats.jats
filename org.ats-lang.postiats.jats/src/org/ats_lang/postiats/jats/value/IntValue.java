package org.ats_lang.postiats.jats.value;

public class IntValue implements ATSValue {
    private Integer m_v;
    
    public IntValue(String num) {
        m_v = new Integer(num);
    }
}
