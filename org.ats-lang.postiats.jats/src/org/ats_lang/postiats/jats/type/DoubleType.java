package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class DoubleType extends ATSPrimType {
//    public static final DoubleType cType = new DoubleType(Decorator.TYPE);
    public static final DoubleType cType0 = new DoubleType(Decorator.T0YPE);
    
    public static final int m_size = 8;

    public static Object fromString(String text) {
        return new Double(text);
    }

//    @Override
//    public String toString() {
//        return DoubleValue.class.getSimpleName();
//    }
    
    @Override
    public int getSize() {
        return m_size;
    }

//    @Override
//    public DoubleValue createDefault() {
//        return new DoubleValue(new Double(0.0));
//
//    }
//
//    @Override
//    public DoubleValue castFrom(PrimValue pv) {
//        return DoubleValue.castFromV(pv);
//    }

    private DoubleType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }

}
