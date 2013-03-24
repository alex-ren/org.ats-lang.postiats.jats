package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.SIntValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class SIntType implements PrimType {
    
    public static final SIntType cType = new SIntType();
    
    public static final int m_size = 4;
    
    private SIntType() {}
    
    public static SIntValue fromString(String text) {
        return new SIntValue(new Integer(text));
    }
    
    @Override
    public String toString() {
        return SIntValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    @Override
    public SIntValue createDefault() {
        return new SIntValue(new Integer(0));
    }

    @Override
    public SIntValue castFrom(PrimValue pv) {
        return SIntValue.castFromV(pv);
    }

}
