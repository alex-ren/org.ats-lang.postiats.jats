package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class LIntType extends ATSEltType {

//    public static final LIntType cType = new LIntType(Decorator.TYPE);
    public static final LIntType cType = new LIntType(Decorator.T0YPE);
    
    public static final int m_size = 4;
    
//    public static LIntValue fromString(String text) {
//        return new LIntValue(new Integer(text));
//    }
//    
    @Override
    public String toString() {
        return Integer.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }
    

    private LIntType(Decorator dec) {
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
//    public LIntValue createDefault() {
//        return new LIntValue(new Integer(0));
//    }
//
//    @Override
//    public LIntValue castFrom(PrimValue pv) {
//        return LIntValue.castFromV(pv);
//    }

    
}
