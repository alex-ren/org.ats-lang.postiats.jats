package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.SingletonValue;

public class VoidType implements ATSType {
    public static final VoidType cType = new VoidType();
    
    public static final int m_size = 0;
//    @Override
//    public String toString() {
//        return "void";
//    }
    
    @Override
    public int getSize() {
        return m_size;
    }
    
	@Override
    public Object createNormalDefault() {
		return SingletonValue.VOID;
    }

	@Override
    public Object createRefDefault() {
	    throw new Error("not supported");
    }

    @Override
    public boolean equals(ATSType ty) {
        return this == ty;
    }


}
