package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.DoubleValue;
import org.ats_lang.postiats.jats.value.FloatValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class F0loatType implements PrimType {

    public static final F0loatType cType = new F0loatType();

    private F0loatType() {
    }

    public static FloatValue fromString(String text) {
        return new FloatValue(new Float(text));
    }

    @Override
    public int getSize() {

        return 8;
    }

    @Override
    public FloatValue createDefault() {
        return new FloatValue(new Float(0.0));

    }

    @Override
    public FloatValue castFrom(PrimValue pv) {
        return FloatValue.castFrom(pv);
    }

}
