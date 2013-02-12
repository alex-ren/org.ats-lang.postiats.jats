package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;

public class I0ntType implements ATSType {
    
    public static final I0ntType cType = new I0ntType();

    private I0ntType () {}

    public static PrimValue fromString(String text) {
        return new PrimValue(new Integer(text));
    }
    
    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public PrimValue createDefault() {
        return new PrimValue(new Integer(0));
    }

	
}
