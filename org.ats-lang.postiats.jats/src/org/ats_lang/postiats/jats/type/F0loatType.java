package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class F0loatType implements ATSType {

    public static final F0loatType cType = new F0loatType();

    private F0loatType() {
    }

    public static ATSValue fromString(String text) {
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
