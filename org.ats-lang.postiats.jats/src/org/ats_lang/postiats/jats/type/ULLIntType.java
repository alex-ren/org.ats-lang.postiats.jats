package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class ULLIntType extends ATSEltType {

//    public static final ULLIntType cType = new ULLIntType(Decorator.TYPE);
    public static final ULLIntType cType = new ULLIntType(Decorator.T0YPE);
    
    public static final int m_size = 8;
    
//	
//    public static ULLIntValue fromString(String text) {
//        return new ULLIntValue(new Long(text));
//    }
    
    @Override
    public String toString() {
        return Integer.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }
    

    private ULLIntType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }

    @Override
    public Object accept(ATSTypeVisitor visitor) {
        return visitor.visit(this);
    }

    
//
//    @Override
//    public ULLIntValue createDefault() {
//        return new ULLIntValue(new Long(0));
//    }
//
//    @Override
//    public ULLIntValue castFrom(PrimValue pv) {
//        return ULLIntValue.castFromV(pv);
//    }

}
