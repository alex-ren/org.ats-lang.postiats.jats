package org.ats_lang.postiats.jats.type;




public class BoolType extends ATSEltType {
//    public static final BoolType cType = new BoolType(Decorator.TYPE);
    public static final BoolType cType0 = new BoolType(Decorator.T0YPE);
    
    public static final int m_size = 1;
    
    private BoolType(Decorator dec) {
        super(dec);
        if (dec != Decorator.T0YPE) {
            throw new Error("Wrong kind");
        }
    }
    
//    @Override
//    public String toString() {
//        return BoolValue.class.getSimpleName();
//    }

    @Override
    public int getSize() {
        return m_size;
    }
    
    
//    public static BoolValue createTrue() {
//        return new BoolValue(true);
//    }
//
//    public static BoolValue createFalse() {
//        return new BoolValue(false);
//    }
//
    public static Object fromString(String text) {

        return new Boolean(text);
    }
    
    
//    // ty := BoolType or RefType(BoolType)
//    public static Boolean isTrue(Object b, ATSType ty) {
//        if (b instanceof Boolean) {
//            return (Boolean) b;
//        } else if (b instanceof Ptrk) {  // 
//            b = ((Ptrk) b).getValue(BoolType.cType0);
//            if (b instanceof Boolean) {
//                return (Boolean) b;
//            } else {
//                throw new Error("Type mismatch");
//            }
//        } else {
//            System.out.println("Boolean.isTrue() object is " + b);
//            throw new Error("Type mismatch");
//        }
//    }

    


}
