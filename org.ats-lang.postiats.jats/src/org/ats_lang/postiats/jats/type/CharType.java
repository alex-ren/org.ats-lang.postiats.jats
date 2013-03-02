package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.CharValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class CharType implements PrimType {
    
    public static final CharType cType = new CharType();
    
    public static final int m_size = 1;

    public static CharValue fromString(String text) {
        return new CharValue(new Character(text.charAt(0)));
    }
    
    @Override
    public String toString() {
        return CharValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

	@Override
    public CharValue createDefault() {
	    return new CharValue(new Character(Character.MIN_VALUE));
    }

    @Override
    public CharValue castFrom(PrimValue pv) {
        return CharValue.castFromV(pv);
    }

}
