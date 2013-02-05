package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class IntType implements ATSType {
    
    public static final IntType cType = new IntType();
    
    public static ATSValue fromString(String text) {
        return new ATSValue(IntType.cType, new Integer(text));
    }
    
    public IntType() {}
    
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 4;
    }

}
