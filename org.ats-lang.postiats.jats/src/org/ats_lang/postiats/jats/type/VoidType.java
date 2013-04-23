package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class VoidType implements ATSType {
    public static final VoidType cType = new VoidType();
    
//    @Override
//    public String toString() {
//        return "void";
//    }
    
    @Override
    public int getSize() {
        return 0;
    }
    
	@Override
    public Object createNormalDefault() {
		return SingletonValue.VOID;
    }

	@Override
    public Object createRefDefault() {
	    throw new Error("not supported");
    }


}
