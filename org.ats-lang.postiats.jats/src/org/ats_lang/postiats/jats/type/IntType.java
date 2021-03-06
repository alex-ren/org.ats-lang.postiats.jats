package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;


public class IntType extends ATSEltType {
    
//    public static final IntType cType = new IntType(Decorator.TYPE);
    public static final IntType cType = new IntType(Decorator.T0YPE);

    public static final int m_size = 4;
    
    @Override
    public int getSize() {
        return m_size;
    }
    
    private IntType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }
    
    
   
    public static Object fromString(String text) {
        return new Integer(text);
    }
    
    public static Integer castFrom(Object v) {
        if (v instanceof Integer) {
            return (Integer) v;
        } else {
            throw new Error("conversion not supported");
        }
    }
    
    @Override
    public String toString() {
        return Integer.class.getSimpleName();
    }

    @Override
    public Object accept(ATSTypeVisitor visitor) {
        return visitor.visit(this);
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


}
