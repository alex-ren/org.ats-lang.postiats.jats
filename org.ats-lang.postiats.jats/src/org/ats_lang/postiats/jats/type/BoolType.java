package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.PrimValue;

public class BoolType implements ATSType {
    public static final BoolType cType = new BoolType();
    
    public static ATSValue createTrue() {
        return new PrimValue(true);
    }
    
    public static ATSValue createFalse() {
        return new PrimValue(false);
    }

    public static ATSValue fromString(String text) {
        
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
