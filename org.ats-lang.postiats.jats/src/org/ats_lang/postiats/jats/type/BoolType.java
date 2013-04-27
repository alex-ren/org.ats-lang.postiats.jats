package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk;



public class BoolType extends ATSPrimType {
//    public static final BoolType cType = new BoolType(Decorator.TYPE);
    public static final BoolType cType0 = new BoolType(Decorator.T0YPE);
    
    public static final int m_size = 1;
 
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
    
    
    // ty := BoolType or RefType(BoolType)
    public static Boolean isTrue(Object b, ATSType ty) {
        if (b instanceof Boolean) {
            return (Boolean) b;
        } else if (b instanceof Ptrk) {  // 
            b = ((Ptrk) b).getValue();
            if (b instanceof Boolean) {
                return (Boolean) b;
            } else {
                throw new Error("Type mismatch");
            }
        } else {
            throw new Error("Type mismatch");
        }
    }

    
    
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

//    @Override
//    public BoolValue createDefault() {
//        return new BoolValue(false);
//    }
//
//    @Override
//    public BoolValue castFrom(PrimValue pv) {
//        return BoolValue.castFromV(pv);
//    }


}
