package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.IntValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class IntType implements PrimType {
    
    public static final IntType cType = new IntType();
    
    private IntType() {}
    
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
