package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class ULIntType implements ATSType {

    public static final ULIntType cType = new ULIntType();
    
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    public static ATSValue fromString(String text) {
        return new ATSValue(ULIntType.cType, new Integer(text));
    }

}
