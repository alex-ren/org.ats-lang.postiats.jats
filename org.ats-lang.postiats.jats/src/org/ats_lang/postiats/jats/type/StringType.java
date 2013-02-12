package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;

public class StringType implements ATSType {

    public static final StringType cType = new StringType();
    
    private StringType() {}
    
    @Override
    public int getSize() {
        return 4;
    }

    public static PrimValue fromString(String text) {
        return new PrimValue(text);
    }

    @Override
    public PrimValue createDefault() {
        return new PrimValue("");
    }

}
