package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.StringValue;

public class StringType implements PrimType {

    public static final StringType cType = new StringType();
    
    public static final int m_size = PtrType.m_size;
    
    private StringType() {}
    
    @Override
    public String toString() {
        return StringValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    public static StringValue fromString(String text) {
        return new StringValue(text);
    }

    @Override
    public StringValue createDefault() {
        return new StringValue("");
    }

    @Override
    public StringValue castFrom(PrimValue pv) {
        return StringValue.castFromV(pv);
    }

}
