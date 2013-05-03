package org.ats_lang.postiats.jats.type;


public class PtrkType extends ATSPrimType { // ATSUpdatableType {

    public static final int m_size = 4;
    public static final PtrkType cType = new PtrkType(VoidType.cType);
//    public static final PtrkType cType0 = new PtrkType(Decorator.T0YPE);
    
    
//    @Override
//    public String toString() {
//        return CCompTypedefs.class.getSimpleName() + "." + CCompTypedefs.CPtrValue.class.getSimpleName();
//    }
    
    @Override
    public int getSize() {
        return m_size;
    }

//    @Override
//    public PtrValue createDefault() {
//        return new PtrValue(SingletonValue.VOID);
//    }
    

    private PtrkType(ATSType ty) {
//        super(ty);
        super(Decorator.TYPE);
    }

//    @Override
//    public PtrkType createUpdatable(ATSType ty) {
//        PtrkType ret = new PtrkType(ty);
//        return ret;
//    }
    
    static public Object deref(Object v) {
        return v;
    }
    
    @Override
    public boolean equals(ATSType ty) {
        if (ty == this || ty instanceof BoxedType) {
            return true;
        } else {
            return false;
        }
    }

}
