package org.ats_lang.postiats.jats.type;

/*
 * Design: The identity of a type is the same as the identity of the corresponding object.
 */

public interface ATSType {
    public enum Decorator {TYPE, T0YPE};
    public enum ParaDecorator {REF0, REF1};
    
	public int getSize();
	
	public Object createDefault();
}
