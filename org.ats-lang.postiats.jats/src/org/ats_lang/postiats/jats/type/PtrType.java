package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class PtrType implements ATSType {
    public static final PtrType cType = new PtrType();
    
    private PtrType() {}
    
    @Override
    public int getSize() {
        return 4;
    }

}
