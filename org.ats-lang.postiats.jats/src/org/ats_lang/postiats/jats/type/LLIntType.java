package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class LLIntType extends ATSEltType {

//    public static final LLIntType cType = new LLIntType(Decorator.TYPE);
    public static final LLIntType cType0 = new LLIntType(Decorator.T0YPE);
    
    public static final int m_size = 8;
    
//
//    public static LLIntValue fromString(String text) {
//        return new LLIntValue(new Long(text));
//    }
//    
//    @Override
//    public String toString() {
//        return LLIntValue.class.getSimpleName();
//    }
//    
    @Override
    public int getSize() {
        return m_size;
    }

    private LLIntType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }

//
//    @Override
//    public LLIntValue createDefault() {
//        return new LLIntValue(new Long(0));
//    }
//
//    @Override
//    public LLIntValue castFrom(PrimValue pv) {
//        return LLIntValue.castFromV(pv);
//    }

    
}
