package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class VoidType implements ATSType {
    public static final VoidType cType = new VoidType();
    
    private VoidType() {}
    
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ATSValue createDefault() {
        return SingletonValue.VOID;
    }

}
