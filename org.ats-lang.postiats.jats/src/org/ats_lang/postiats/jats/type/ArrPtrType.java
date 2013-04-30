package org.ats_lang.postiats.jats.type;

public class ArrPtrType extends ATSPrimType {  // ATSUpdatableType {

    public static final ArrPtrType cType = new ArrPtrType(VoidType.cType);
    
    @Override
    public int getSize() {
        return PtrkType.cType.getSize();
    }

    private ArrPtrType(ATSType ty) {
//        super(ty);
        super(Decorator.TYPE);
    }
    
//    @Override
//    public ArrPtrType createUpdatable(ATSType ty) {
//        ArrPtrType ret = new ArrPtrType(ty);
//        return ret;
//    }

}
