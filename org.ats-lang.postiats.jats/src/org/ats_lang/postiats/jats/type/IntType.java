package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class IntType extends PrimType {
    
//    public static final IntType cType = new IntType(Decorator.TYPE);
    public static final IntType cType0 = new IntType(Decorator.T0YPE);
    
    
    public static final int m_size = 4;
    
   
//    public static IntValue fromString(String text) {
//        return new IntValue(new Integer(text));
//    }
//    
//    @Override
//    public String toString() {
//        return IntValue.class.getSimpleName();
//    }
//    
    @Override
    public int getSize() {
        return m_size;
    }
//
//    @Override
//    public IntValue createDefault() {
//        return new IntValue(new Integer(0));
//    }
//
//    @Override
//    public IntValue castFrom(PrimValue pv) {
//        return IntValue.castFromV(pv);
//    }

    private IntType(Decorator dec) {
        super(dec);
    }

}
