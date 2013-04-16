package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.type.ATSKindType.Decorator;

public class PtrkType extends ATSKindType {

    public static final int m_size = 4;

    public static final PtrkType cType = new PtrkType(Decorator.TYPE);
    public static final PtrkType cType0 = new PtrkType(Decorator.T0YPE);
    
    
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
    

    private PtrkType(Decorator dec) {
        super(dec);
    }

}
