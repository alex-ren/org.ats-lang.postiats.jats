package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.BoolValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class BoolType implements PrimType {
    public static final BoolType cType = new BoolType();

    public static BoolValue createTrue() {
        return new BoolValue(true);
    }

    public static BoolValue createFalse() {
        return new BoolValue(false);
    }

    public static BoolValue fromString(String text) {

        return new BoolValue(new Boolean(text));
    }

    private BoolType() {
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public BoolValue createDefault() {
        return new BoolValue(false);
    }

    @Override
    public BoolValue castFrom(PrimValue pv) {
        return BoolValue.castFrom(pv);
    }

}
