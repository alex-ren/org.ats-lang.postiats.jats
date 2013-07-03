package org.ats_lang.postiats.jats.type;



public class DoubleType extends ATSEltType {
//    public static final DoubleType cType = new DoubleType(Decorator.TYPE);
    public static final DoubleType cType0 = new DoubleType(Decorator.T0YPE);
    
    public static final int m_size = 8;


    @Override
    public String toString() {
        return Double.class.getSimpleName();
    }
    

    private DoubleType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }

    
    @Override
    public int getSize() {
        return m_size;
    }


    public static Object fromString(String text) {
        return new Double(text);
    }
    
//    @Override
//    public DoubleValue createDefault() {
//        return new DoubleValue(new Double(0.0));
//
//    }
//
//    @Override
//    public DoubleValue castFrom(PrimValue pv) {
//        return DoubleValue.castFromV(pv);
//    }

}
