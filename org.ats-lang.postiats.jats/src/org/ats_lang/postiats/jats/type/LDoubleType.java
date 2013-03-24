package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.LDoubleValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class LDoubleType implements PrimType {
    public static final LDoubleType cType = new LDoubleType();
    
    public static final int m_size = 8;

    private LDoubleType () {}

    public static LDoubleValue fromString(String text) {
        return new LDoubleValue(new Double(text));
    }

    @Override
    public String toString() {
        return LDoubleValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    @Override
    public LDoubleValue createDefault() {
        return new LDoubleValue(new Double(0.0));

    }

    @Override
    public LDoubleValue castFrom(PrimValue pv) {
        return LDoubleValue.castFromV(pv);
    }

}
