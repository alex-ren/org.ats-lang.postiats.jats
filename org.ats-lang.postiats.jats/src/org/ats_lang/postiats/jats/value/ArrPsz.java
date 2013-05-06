package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSReferableType;
import org.ats_lang.postiats.jats.type.ATSType;


public class ArrPsz {
    private int m_asz;
    private Object[] m_ptr;
    private ATSReferableType m_elety;

    public ArrPsz(ATSReferableType elety) {
        m_asz = 0;
        m_ptr = null;
    }
    
    public void setAsz(int asz) {
        m_asz = asz;
    }
    
    public void setPtr(Object[] ptr) {
        m_ptr = ptr;
    }
    
    public void setElementType(ATSReferableType elety) {
        m_elety = elety;
    }
    
    public int getAsz() {
        return m_asz;
    }
    
    public Object[] getPtr() {
        return m_ptr;
    }
    
    public ATSReferableType getElementType() {
        return m_elety;
    }
}
