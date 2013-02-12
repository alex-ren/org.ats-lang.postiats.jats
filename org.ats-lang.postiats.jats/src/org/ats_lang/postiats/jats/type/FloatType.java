package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;

public class FloatType implements ATSType {
    
    public static final FloatType cType = new FloatType();

    private FloatType () {}

    public static PrimValue fromString(String text) {
        return new PrimValue(new Float(text));
    }

    @Override
    public int getSize() {
        return 8;
    }

    @Override
    public PrimValue createDefault() {
        return new PrimValue(new Float(0.0));

    }
}
