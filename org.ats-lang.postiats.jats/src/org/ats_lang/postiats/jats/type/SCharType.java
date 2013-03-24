package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.SCharValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class SCharType implements PrimType {
    
    public static final SCharType cType = new SCharType();
    
    public static final int m_size = 1;

    public static SCharValue fromString(String text) {
        return new SCharValue(new Character(text.charAt(0)));
    }
    
    @Override
    public String toString() {
        return SCharValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

	@Override
    public SCharValue createDefault() {
	    return new SCharValue(new Character(Character.MIN_VALUE));
    }

    @Override
    public SCharValue castFrom(PrimValue pv) {
        return SCharValue.castFromV(pv);
    }

}
