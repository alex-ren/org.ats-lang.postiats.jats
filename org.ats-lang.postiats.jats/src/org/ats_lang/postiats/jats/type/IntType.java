package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class IntType implements PrimType {
    
    public static final IntType cType = new IntType();
    
    public static final int m_size = 4;
    
    private IntType() {}
    
    public static IntValue fromString(String text) {
        return new IntValue(new Integer(text));
    }
    
    @Override
    public String toString() {
        return IntValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    @Override
    public IntValue createDefault() {
        return new IntValue(new Integer(0));
    }

    @Override
    public IntValue castFrom(PrimValue pv) {
        return IntValue.castFromV(pv);
    }

}
