package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class UIntType extends ATSPrimType {

//    public static final UIntType cType = new UIntType(Decorator.TYPE);
    public static final UIntType cType0 = new UIntType(Decorator.T0YPE);
    
    public static final int m_size = 4;
    
	
//    public static UIntValue fromString(String text) {
//        return new UIntValue(new Integer(text));
//    }
//    
//    @Override
//    public String toString() {
//        return UIntValue.class.getSimpleName();
//    }
    
    @Override
    public int getSize() {
        return m_size;
    }

//    @Override
//    public UIntValue createDefault() {
//        return new UIntValue(new Integer(0));
//    }
//
//    @Override
//    public UIntValue castFrom(PrimValue pv) {
//        return UIntValue.castFromV(pv);
//    }

    private UIntType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }

}
