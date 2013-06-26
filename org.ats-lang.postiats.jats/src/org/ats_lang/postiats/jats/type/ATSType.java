package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk;



public interface ATSType {
    
    
	public int getSize();
	
	boolean equals(ATSType ty);
	
	Object createNormalDefault();
	
	Ptrk createRefDefault();
	
//	
//    public String toString();

}
