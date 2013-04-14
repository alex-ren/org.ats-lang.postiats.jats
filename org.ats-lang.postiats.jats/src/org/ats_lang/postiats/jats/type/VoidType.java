package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class VoidType extends ATSKindType {
    public static final VoidType cType = new VoidType();
    
//    @Override
//    public String toString() {
//        return "void";
//    }
    
    @Override
    public int getSize() {
        return 0;
    }
//
//    @Override
//    public ATSValue createDefault() {
//        return SingletonValue.VOID;
//    }

    private VoidType() {
        super(Decorator.T0YPE);
    }


}
