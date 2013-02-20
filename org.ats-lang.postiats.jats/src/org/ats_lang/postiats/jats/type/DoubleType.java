package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.DoubleValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class DoubleType implements PrimType {
    public static final DoubleType cType = new DoubleType();

    private DoubleType () {}

    public static DoubleValue fromString(String text) {
        return new DoubleValue(new Double(text));
    }

    @Override
    public int getSize() {
        return 8;
    }

    @Override
    public DoubleValue createDefault() {
        return new DoubleValue(new Double(0.0));

    }

    @Override
    public DoubleValue castFrom(PrimValue pv) {
        return DoubleValue.castFromV(pv);
    }

}
