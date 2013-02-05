package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class CharType implements ATSType {
    
    public static final CharType cType = new CharType();
    
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 1;
    }

    public static ATSValue fromString(String text) {
        return new ATSValue(CharType.cType, new Character(text.charAt(0)));
    }

}
