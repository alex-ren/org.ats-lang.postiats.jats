package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.value.Ptrk.ArrayElement;

public class ArrPtr {
    private ArrayElement m_content;
    
    
    private ArrPtr(Object[] arr, ATSType elety) {
        m_content = new ArrayElement(arr, 0, elety);
    }
    
    public ArrPtr(ArrPsz arrpsz) {
        this(arrpsz.getPtr(), arrpsz.getElementType());
    }
    
    public ArrPtr(ArrayElement obj) {
        m_content = obj;
    }
    
    public void inc() {
        m_content.inc();
    }
    
    public void dec() {
        m_content.dec();
    }


}
