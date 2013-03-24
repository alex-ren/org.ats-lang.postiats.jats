package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.ULLIntValue;

public class ULLIntType implements PrimType {

    public static final ULLIntType cType = new ULLIntType();
    
    public static final int m_size = 8;
    
    private ULLIntType() {}
	
    public static ULLIntValue fromString(String text) {
        return new ULLIntValue(new Long(text));
    }
    
    @Override
    public String toString() {
        return ULLIntType.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    @Override
    public ULLIntValue createDefault() {
        return new ULLIntValue(new Long(0));
    }

    @Override
    public ULLIntValue castFrom(PrimValue pv) {
        return ULLIntValue.castFromV(pv);
    }
}
