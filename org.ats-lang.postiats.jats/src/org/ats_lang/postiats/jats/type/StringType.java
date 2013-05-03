package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.Ptrk.StringElement;

/*
 * This type is not currently used in the system.
 */

public class StringType {

//    public static final StringType cType = new StringType(Decorator.TYPE); 
    
//    private StringType(Decorator dec) {
//        super(dec);
//    }
    
//    public static final int m_size = PtrType.m_size;
    
//    @Override
//    public String toString() {
//        return StringValue.class.getSimpleName();
//    }
//    
//    
//    @Override
//    public int getSize() {
//        throw new Error("not supported");
//    }
//
//    @Override
//    public Object createNormalDefault() {
//        throw new Error("not supported");
//    }
//
//    @Override
//    public Object createRefDefault() {
//        // TODO Auto-generated method stub
//        throw new Error("not supported");
//    }

    public static Ptrk fromString(String text) {
        char[] str = new char[text.length() + 1];
    	for (int i = 0; i < text.length(); ++i) {
    		str[i] = text.charAt(i);
    	}
    	str[str.length - 1] = Character.MIN_VALUE;  // '\u0000'
    	StringElement strele = new StringElement(str, 0);
        return new Ptrk(strele);
    }
    
    public static String toString(char[] buf) {
        return new String(buf, 0, buf.length - 1);
    }
    
    public static String toString(char[] buf, int start, int len) {
        return new String(buf, start, len);
    }
    

    public static String toString(Ptrk msg) {
        return msg.formString();
    }
//
//    @Override
//    public StringValue createDefault() {
//        return new StringValue("");
//    }

//    private StringType(Decorator dec) {
//        super(dec);
//    }

}
