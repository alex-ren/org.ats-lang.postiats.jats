package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class UCharType extends ATSEltType {
    
//    public static final UCharType cType = new UCharType(Decorator.TYPE);
    public static final UCharType cType = new UCharType(Decorator.T0YPE);
    
    public static final int m_size = 1;

//    public static UCharValue fromString(String text) {
//        return new UCharValue(new Character(text.charAt(0)));
//    }
    
    @Override
    public String toString() {
        return Character.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }
    

    private UCharType(Decorator dec) {
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
//	@Override
//    public UCharValue createDefault() {
//	    return new UCharValue(new Character(Character.MIN_VALUE));
//    }
//
//    @Override
//    public UCharValue castFrom(PrimValue pv) {
//        return UCharValue.castFromV(pv);
//    }


}
