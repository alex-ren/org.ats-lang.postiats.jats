package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.LIntValue;

public class LIntType implements PrimType {

    public static final LIntType cType = new LIntType();
    
    public static final int m_size = 4;
    
    private LIntType() {}
	
    public static LIntValue fromString(String text) {
        return new LIntValue(new Integer(text));
    }
    
    @Override
    public String toString() {
        return LIntValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    @Override
    public LIntValue createDefault() {
        return new LIntValue(new Integer(0));
    }

    @Override
    public LIntValue castFrom(PrimValue pv) {
        return LIntValue.castFromV(pv);
    }
}
