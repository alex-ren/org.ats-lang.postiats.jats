package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class BoolType implements ATSType {
    public static final BoolType cType = new BoolType();
    
    public static ATSValue createTrue() {
        return new ATSValue(BoolType.cType, true);
    }
    
    public static ATSValue createFalse() {
        return new ATSValue(BoolType.cType, false);
    }
    
    private BoolType() {}
    
    @Override
    public int getSize() {
        return 1;
    }

    public static ATSValue fromString(String text) {
        
        return new ATSValue(BoolType.cType, new Boolean(text));
    }

}
