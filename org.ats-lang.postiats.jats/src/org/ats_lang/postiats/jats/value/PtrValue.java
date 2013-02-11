package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.PtrType;

public class PtrValue implements ATSValue {
    
    private ATSValue m_mem;
    // private static final PtrType m_type = PtrType.cType;

    // for pointer
    public PtrValue(ATSValue v) {
    	m_mem = v;
    }
    
	public ATSValue deRef() {
		return m_mem;
	}

	@Override
	public void copyfrom(ATSValue v) {
		if (v instanceof PtrValue) {
			m_mem = ((PtrValue)v).deRef();
		} else {
    		throw new Error("PtrValue::copyfrom: copy from non-pointer value.");
    	}
	}

	@Override
	public ATSValue getContent() {
		return m_mem;
	}

}
