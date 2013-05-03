package org.ats_lang.postiats.jats.type;



public interface ATSType {
    
    
	public int getSize();
	
	Object createNormalDefault();
	Object createRefDefault();
	boolean equals(ATSType ty);
//	
//    public String toString();

}
