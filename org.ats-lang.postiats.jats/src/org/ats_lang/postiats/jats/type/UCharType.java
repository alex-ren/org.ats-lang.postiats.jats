package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.UCharValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class UCharType implements PrimType {
    
    public static final UCharType cType = new UCharType();
    
    public static final int m_size = 1;

    public static UCharValue fromString(String text) {
        return new UCharValue(new Character(text.charAt(0)));
    }
    
    @Override
    public String toString() {
        return UCharValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

	@Override
    public UCharValue createDefault() {
	    return new UCharValue(new Character(Character.MIN_VALUE));
    }

    @Override
    public UCharValue castFrom(PrimValue pv) {
        return UCharValue.castFromV(pv);
    }

}