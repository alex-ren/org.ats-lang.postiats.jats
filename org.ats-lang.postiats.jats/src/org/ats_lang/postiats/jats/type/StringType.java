package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class StringType implements ATSType {

    public static final StringType cType = new StringType();
    
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 4;
    }

    public static ATSValue fromString(String text) {
        return new ATSValue(StringType.cType, text);
    }

}
