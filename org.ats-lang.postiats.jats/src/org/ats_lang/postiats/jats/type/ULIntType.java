package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.ULIntValue;

public class ULIntType implements PrimType {

    public static final ULIntType cType = new ULIntType();
    
    public static final int m_size = 4;
    
    private ULIntType() {}
	
    public static ULIntValue fromString(String text) {
        return new ULIntValue(new Integer(text));
    }
    
    @Override
    public String toString() {
        return ULIntValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    @Override
    public ULIntValue createDefault() {
        return new ULIntValue(new Integer(0));
    }

    @Override
    public ULIntValue castFrom(PrimValue pv) {
        return ULIntValue.castFromV(pv);
    }
}
