package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class F0loatType implements ATSType {

    public static final F0loatType cType = new F0loatType();

    private F0loatType () {}
    
    @Override
    public int getSize() {

        return 8;
    }

    public static ATSValue fromString(String text) {
        return new ATSValue(F0loatType.cType, new Float(text));
    }

}
