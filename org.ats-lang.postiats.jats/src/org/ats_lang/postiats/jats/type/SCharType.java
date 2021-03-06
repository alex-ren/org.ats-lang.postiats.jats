package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class SCharType extends ATSEltType {
    
//    public static final SCharType cType = new SCharType(Decorator.TYPE);
    public static final SCharType cType = new SCharType(Decorator.T0YPE);
    
    public static final int m_size = 1;
//
//    public static SCharValue fromString(String text) {
//        return new SCharValue(new Character(text.charAt(0)));
//    }
    
    @Override
    public String toString() {
        return Character.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }
    
    private SCharType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }
//
//	@Override
//    public SCharValue createDefault() {
//	    return new SCharValue(new Character(Character.MIN_VALUE));
//    }
//
//    @Override
//    public SCharValue castFrom(PrimValue pv) {
//        return SCharValue.castFromV(pv);
//    }

    @Override
    public Object accept(ATSTypeVisitor visitor) {
        return visitor.visit(this);
    }



}
