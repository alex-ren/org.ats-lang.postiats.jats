package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class SizeType extends ATSEltType {

//    public static final SizeType cType = new SizeType(Decorator.TYPE);
    public static final SizeType cType = new SizeType(Decorator.T0YPE);
    
    public static final int m_size = 4;
    
    @Override
    public int getSize() {
        return m_size;
    }

    private SizeType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }
//    public static SizeValue fromString(String text) {
//        return new SizeValue(new Integer(text));
//    }
    
    @Override
    public String toString() {
        return Integer.class.getSimpleName();
    }
    
    public static Integer castFrom(Object v) {
        if (v instanceof Integer) {
            return (Integer) v;
        } else {
            throw new Error("conversion not supported");
        }
    }

    @Override
    public Object accept(ATSTypeVisitor visitor) {
        return visitor.visit(this);
    }
    

//    @Override
//    public SizeValue createDefault() {
//        return new SizeValue(new Integer(0));
//    }
//
//    @Override
//    public SizeValue castFrom(PrimValue pv) {
//        return SizeValue.castFromV(pv);
//    }


}
