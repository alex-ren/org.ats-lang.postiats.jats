package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class VoidType implements ATSType {
    public static final VoidType cType = new VoidType();
    
    public static final int m_size = 0;
    
    @Override
    public String toString() {
        return SingletonValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }
    
	@Override
    public Object createNormalDefault() {
		return SingletonValue.VOID;
    }

	@Override
    public Ptrk createRefDefault() {
	    throw new Error("not supported");
    }

    @Override
    public boolean equals(ATSType ty) {
        return this == ty;
    }

    @Override
    public Object accept(ATSTypeVisitor visitor) {
        return visitor.visit(this);
    }


}
