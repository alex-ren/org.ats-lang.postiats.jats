package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;

public class SIntType extends ATSEltType {
    
//    public static final SIntType cType = new SIntType(Decorator.TYPE);
    public static final SIntType cType = new SIntType(Decorator.T0YPE);
    
    public static final int m_size = 4;
    
//    
//    public static SIntValue fromString(String text) {
//        return new SIntValue(new Integer(text));
//    }
    
    @Override
    public String toString() {
        return Integer.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }
//
//    @Override
//    public SIntValue createDefault() {
//        return new SIntValue(new Integer(0));
//    }
//
//    @Override
//    public SIntValue castFrom(PrimValue pv) {
//        return SIntValue.castFromV(pv);
//    }

    private SIntType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }

    @Override
    public Object accept(ATSTypeVisitor visitor) {
        return visitor.visit(this);
    }

}
