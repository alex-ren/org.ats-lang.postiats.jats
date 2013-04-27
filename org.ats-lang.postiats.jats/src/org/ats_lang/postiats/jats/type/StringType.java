package org.ats_lang.postiats.jats.type;



public class StringType extends ATSKindType {

    public static final StringType cType = new StringType(Decorator.TYPE); 
    
    public StringType(Decorator dec) {
        super(dec);
    }
    
//    public static final int m_size = PtrType.m_size;
    
//    @Override
//    public String toString() {
//        return StringValue.class.getSimpleName();
//    }
//    
    
    @Override
    public int getSize() {
        throw new Error("not supported");
    }

    @Override
    public Object createNormalDefault() {
        throw new Error("not supported");
    }

    @Override
    public Object createRefDefault() {
        // TODO Auto-generated method stub
        throw new Error("not supported");
    }

    public static Character[] fromString(String text) {
    	Character[] str = new Character[text.length() + 1];
    	for (int i = 0; i < text.length(); ++i) {
    		str[i] = text.charAt(i);
    	}
    	str[str.length - 1] = Character.MIN_VALUE;  // '\u0000'
        return str;
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
