package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.SizeValue;

public class SizeType implements PrimType {

    public static final SizeType cType = new SizeType();
    
    public static final int m_size = 4;
    
    private SizeType() {}
    
    public static SizeValue fromString(String text) {
        return new SizeValue(new Integer(text));
    }
    
    @Override
    public String toString() {
        return SizeValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    @Override
    public SizeValue createDefault() {
        return new SizeValue(new Integer(0));
    }

    @Override
    public SizeValue castFrom(PrimValue pv) {
        return SizeValue.castFromV(pv);
    }

}