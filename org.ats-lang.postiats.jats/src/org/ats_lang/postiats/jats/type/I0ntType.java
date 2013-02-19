package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.FloatValue;
import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class I0ntType implements PrimType {
    
    public static final I0ntType cType = new I0ntType();

    private I0ntType () {}

    public static IntValue fromString(String text) {
        return new IntValue(new Integer(text));
    }
    
    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public IntValue createDefault() {
        return new IntValue(new Integer(0));
    }

    @Override
    public IntValue castFrom(PrimValue pv) {
        return IntValue.castFrom(pv);
    }

}
