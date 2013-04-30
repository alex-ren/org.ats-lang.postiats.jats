package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;


public class ArrayType extends ATSPrimType {  // ATSUpdatableType {
    
    public static final ArrayType cType = new ArrayType(VoidType.cType);
    
    private ArrayType(ATSType type) {
//        super(type);
        super(Decorator.TYPE);
    }
//
//    @Override
//    public String toString() {
//        return ArrayValue.class.getSimpleName();
//    }
//    
    @Override
    public int getSize() {
        throw new Error("Not supported");
//        return PtrkType.cType.getSize();
    }
    
//    @Override
//    public ArrayType createUpdatable(ATSType ty) {
//        ArrayType ret = new ArrayType(ty);
//        return ret;
//    }
    
    @Override
    public SingletonValue createNormalDefault() {
        throw new Error("Not supported");
    }
    
    @Override
    public Ptrk createRefDefault() {
        throw new Error("Not supported");
    }
    
    @Override
    public Decorator getKind() {
        throw new Error("Not supported");
    }

}
