package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;


public interface ATSType {
    
    
	public int getSize();
	
	Object createNormalDefault();
	Object createRefDefault();
//	
//    public String toString();
}
