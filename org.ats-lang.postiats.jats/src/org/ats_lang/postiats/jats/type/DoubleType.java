package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.DoubleValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class DoubleType extends PrimType {
//    public static final DoubleType cType = new DoubleType(Decorator.TYPE);
    public static final DoubleType cType0 = new DoubleType(Decorator.T0YPE);
    
    public static final int m_size = 8;

//    public static DoubleValue fromString(String text) {
//        return new DoubleValue(new Double(text));
//    }

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
    }

}
