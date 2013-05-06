package org.ats_lang.postiats.jats.type;



public interface ATSType {
    
    
	public int getSize();
	
	boolean equals(ATSType ty);
	
	Object createNormalDefault();
	
	Object createRefDefault();
	
//	
//    public String toString();

}
