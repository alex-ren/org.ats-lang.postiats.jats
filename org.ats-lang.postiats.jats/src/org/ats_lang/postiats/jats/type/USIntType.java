package org.ats_lang.postiats.jats.type;

public class USIntType extends ATSEltType {
    
//    public static final USIntType cType = new USIntType(Decorator.TYPE);
    public static final USIntType cType = new USIntType(Decorator.T0YPE);
    
    public static final int m_size = 4;
//    
//    public static USIntValue fromString(String text) {
//        return new USIntValue(new Integer(text));
//    }
    
    @Override
    public String toString() {
        return Integer.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    private USIntType(Decorator dec) {
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
//    public USIntValue createDefault() {
//        return new USIntValue(new Integer(0));
//    }
//
//    @Override
//    public USIntValue castFrom(PrimValue pv) {
//        return USIntValue.castFromV(pv);
//    }


}
