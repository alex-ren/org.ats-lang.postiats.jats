package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;

public class BoolType implements ATSType {
    public static final BoolType cType = new BoolType();
    
    public static PrimValue createTrue() {
        return new PrimValue(true);
    }
    
    public static PrimValue createFalse() {
        return new PrimValue(false);
    }

    public static PrimValue fromString(String text) {
        
        return new PrimValue(new Boolean(text));
    }

    private BoolType() {}
    
    @Override
    public int getSize() {
        return 1;
    }

	@Override
    public PrimValue createDefault() {
	    return new PrimValue(false);
    }


}
