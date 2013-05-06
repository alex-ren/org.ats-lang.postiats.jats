package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSReferableType;
import org.ats_lang.postiats.jats.type.ArrayType;
import org.ats_lang.postiats.jats.value.ArrPsz;
import org.ats_lang.postiats.jats.value.Ptrk.Location;
import org.ats_lang.postiats.jats.value.Ptrk.Mem;

public class ArrPtr {
	private ATSReferableType m_elety;
	private int m_index;
    private Mem m_content;
    
    
    private ArrPtr(Object[] arr, ATSReferableType elety) {
        m_content = new Mem(new ArrayType(elety, arr.length), arr);
        m_elety = elety;
        m_index = 0;
    }
    
    public ArrPtr(ArrPsz arrpsz) {
        this(arrpsz.getPtr(), arrpsz.getElementType());
    }
    
    public void update(Object v, ATSReferableType elety) {
    	Location loc = m_content.getLoc(m_index * m_elety.getSize(), elety);
    	loc.update(v);
    }

    public void inc() {
    	m_index++;
    }
    
    public void dec() {
    	m_index--;
    }


}
