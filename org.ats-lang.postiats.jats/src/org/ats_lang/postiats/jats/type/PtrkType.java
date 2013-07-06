package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk;


public class PtrkType extends ATSEltType { // ATSUpdatableType {

    public static final int m_size = 4;
    
    public static final PtrkType cType = new PtrkType(VoidType.cType);
//    public static final PtrkType cType0 = new PtrkType(Decorator.T0YPE);
    
    
    public String toString() {
        return Ptrk.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }
    

    private PtrkType(ATSType ty) {
//        super(ty);
        super(Decorator.TYPE);
    }


//    @Override
//    public PtrValue createDefault() {
//        return new PtrValue(SingletonValue.VOID);
//    }
    

//    @Override
//    public PtrkType createUpdatable(ATSType ty) {
//        PtrkType ret = new PtrkType(ty);
//        return ret;
//    }
    
    
    @Override
    public boolean equals(ATSType ty) {
        if (ty == this || ty instanceof BoxedType) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Object accept(ATSTypeVisitor visitor) {
        return visitor.visit(this);
    }

}
