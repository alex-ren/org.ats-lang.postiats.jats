package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class SSizeType extends ATSEltType {
    
//    public static final SSizeType cType = new SSizeType(Decorator.TYPE);
    public static final SSizeType cType0 = new SSizeType(Decorator.T0YPE);
    
    public static final int m_size = 4;
    
    
//    public static SSizeValue fromString(String text) {
//        return new SSizeValue(new Integer(text));
//    }
    
    @Override
    public String toString() {
        return Integer.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    private SSizeType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }

//    @Override
//    public SSizeValue createDefault() {
//        return new SSizeValue(new Integer(0));
//    }
//
//    @Override
//    public SSizeValue castFrom(PrimValue pv) {
//        return SSizeValue.castFromV(pv);
//    }

}
