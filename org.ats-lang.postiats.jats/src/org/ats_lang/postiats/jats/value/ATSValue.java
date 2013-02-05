package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSType;

public class ATSValue {
    
    private Object m_mem;
    private ATSType m_type;
    
    public ATSValue(ATSType type, Object mem) {
        m_type = type;
        m_mem = mem;
    }
    
}
