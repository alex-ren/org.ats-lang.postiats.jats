package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.ccomp.CCompTypedefs;
import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;
import org.ats_lang.postiats.jats.value.PtrValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class PtrType implements ATSType {
    public static final int m_size = 4;

    public static final PtrType cType = new PtrType();
//    public static final PtrType cType0 = new PtrType(Decorator.T0YPE);
    
    
//    @Override
//    public String toString() {
//        return CCompTypedefs.class.getSimpleName() + "." + CCompTypedefs.CPtrValue.class.getSimpleName();
//    }
    
    @Override
    public int getSize() {
        return m_size;
    }


    @Override
    public Object createDefault() {
        // TODO Auto-generated method stub
        return null;
    }

//    @Override
//    public PtrValue createDefault() {
//        return new PtrValue(SingletonValue.VOID);
//    }

}
