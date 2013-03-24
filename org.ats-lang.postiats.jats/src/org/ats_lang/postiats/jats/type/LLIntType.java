package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.LLIntValue;

public class LLIntType implements PrimType {

    public static final LLIntType cType = new LLIntType();
    
    public static final int m_size = 8;
    
    private LLIntType() {}
	
    public static LLIntValue fromString(String text) {
        return new LLIntValue(new Long(text));
    }
    
    @Override
    public String toString() {
        return LLIntValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    @Override
    public LLIntValue createDefault() {
        return new LLIntValue(new Long(0));
    }

    @Override
    public LLIntValue castFrom(PrimValue pv) {
        return LLIntValue.castFromV(pv);
    }
}
