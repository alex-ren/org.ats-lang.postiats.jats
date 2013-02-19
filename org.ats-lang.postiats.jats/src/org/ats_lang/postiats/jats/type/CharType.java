package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.CharValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class CharType implements PrimType {
    
    public static final CharType cType = new CharType();

    public static CharValue fromString(String text) {
        return new CharValue(new Character(text.charAt(0)));
    }
    
    @Override
    public int getSize() {
        return 1;
    }

	@Override
    public CharValue createDefault() {
	    return new CharValue(new Character(Character.MIN_VALUE));
    }

    @Override
    public CharValue castFrom(PrimValue pv) {
        return CharValue.castFrom(pv);
    }

}
