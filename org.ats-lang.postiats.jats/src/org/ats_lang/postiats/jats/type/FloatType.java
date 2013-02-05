package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class FloatType implements ATSType {
    
    public static final FloatType cType = new FloatType();

    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    public static ATSValue fromString(String text) {
        return new ATSValue(FloatType.cType, new Float(text));
    }

}
