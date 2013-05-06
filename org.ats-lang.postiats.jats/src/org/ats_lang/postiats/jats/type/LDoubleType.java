package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class LDoubleType extends ATSEltType {
//    public static final LDoubleType cType = new LDoubleType(Decorator.TYPE);
    public static final LDoubleType cType0 = new LDoubleType(Decorator.T0YPE);
    
    public static final int m_size = 8;
//
//    public static LDoubleValue fromString(String text) {
//        return new LDoubleValue(new Double(text));
//    }
//
//    @Override
//    public String toString() {
//        return LDoubleValue.class.getSimpleName();
//    }
//    
    @Override
    public int getSize() {
        return m_size;
    }
    

    private LDoubleType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }
    
//
//    @Override
//    public LDoubleValue createDefault() {
//        return new LDoubleValue(new Double(0.0));
//
//    }
//
//    @Override
//    public LDoubleValue castFrom(PrimValue pv) {
//        return LDoubleValue.castFromV(pv);
//    }


}
