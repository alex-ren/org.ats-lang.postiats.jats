package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;
import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.ULIntValue;

public class ULIntType extends PrimType {

//    public static final ULIntType cType = new ULIntType(Decorator.TYPE);
    public static final ULIntType cType0 = new ULIntType(Decorator.T0YPE);
    
    public static final int m_size = 4;
//    
//    public static ULIntValue fromString(String text) {
//        return new ULIntValue(new Integer(text));
//    }
//    
//    @Override
//    public String toString() {
//        return ULIntValue.class.getSimpleName();
//    }
//    
    @Override
    public int getSize() {
        return m_size;
    }
//
//    @Override
//    public ULIntValue createDefault() {
//        return new ULIntValue(new Integer(0));
//    }
//
//    @Override
//    public ULIntValue castFrom(PrimValue pv) {
//        return ULIntValue.castFromV(pv);
//    }

    private ULIntType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }

}
