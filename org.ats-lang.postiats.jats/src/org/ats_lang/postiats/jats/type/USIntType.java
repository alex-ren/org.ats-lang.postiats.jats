package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.USIntValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class USIntType implements PrimType {
    
    public static final USIntType cType = new USIntType();
    
    public static final int m_size = 4;
    
    private USIntType() {}
    
    public static USIntValue fromString(String text) {
        return new USIntValue(new Integer(text));
    }
    
    @Override
    public String toString() {
        return USIntValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    @Override
    public USIntValue createDefault() {
        return new USIntValue(new Integer(0));
    }

    @Override
    public USIntValue castFrom(PrimValue pv) {
        return USIntValue.castFromV(pv);
    }

}
