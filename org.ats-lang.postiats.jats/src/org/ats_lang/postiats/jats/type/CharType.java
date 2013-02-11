package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class CharType implements ATSType {
    
    public static final CharType cType = new CharType();

    public static ATSValue fromString(String text) {
        return new PrimValue(new Character(text.charAt(0)));
    }
    
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 1;
    }

	@Override
    public PrimValue createDefault() {
	    return new PrimValue(Character.MIN_VALUE);
    }


}
