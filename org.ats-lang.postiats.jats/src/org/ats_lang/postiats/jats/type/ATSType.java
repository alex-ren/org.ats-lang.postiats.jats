package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

/*
 * Design: The identity of a type is the same as the identity of the corresponding object.
 */

public interface ATSType {
    static public enum Decorator {TYPE, T0YPE};
    
	public int getSize();
	
	public ATSValue createDefault();
}
