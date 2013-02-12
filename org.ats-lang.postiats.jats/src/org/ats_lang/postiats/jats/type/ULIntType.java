package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;

public class ULIntType implements ATSType {

    public static final ULIntType cType = new ULIntType();
    
    private ULIntType() {}
	
    public static PrimValue fromString(String text) {
        return new PrimValue(ULIntType.cType, new Integer(text));
    }
    
    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public PrimValue createDefault() {
        return new PrimValue(ULIntType.cType, new Integer(0));
    }
}
