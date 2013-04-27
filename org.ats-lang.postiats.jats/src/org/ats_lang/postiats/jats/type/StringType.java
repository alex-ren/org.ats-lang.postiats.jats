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

//    public static StringValue fromString(String text) {
//        return new StringValue(text);
//    }
//
//    @Override
//    public StringValue createDefault() {
//        return new StringValue("");
//    }

//    private StringType(Decorator dec) {
//        super(dec);
//    }

}
