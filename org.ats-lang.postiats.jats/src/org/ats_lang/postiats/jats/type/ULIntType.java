package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.ULIntValue;

public class ULIntType implements PrimType {

    public static final ULIntType cType = new ULIntType();
    
    private ULIntType() {}
	
    public static ULIntValue fromString(String text) {
        return new ULIntValue(new Integer(text));
    }
    
    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public ULIntValue createDefault() {
        return new ULIntValue(new Integer(0));
    }

    @Override
    public ULIntValue castFrom(PrimValue pv) {
        return ULIntValue.castFrom(pv);
    }
}
