package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PtrValue;

public class RefType implements ATSType {
	private ATSType m_type;
	
	public RefType(ATSType type) {
		m_type = type;
	}

	public ATSType defType() {
		return m_type;
	}
	
	public void update(Object holder, Object src, ATSType realty) {
		if (realty instanceof StructType) {
			
		} else if (realty instanceof ArrayType) {
			
		} else if (realty instanceof RefType) {
			
		} else {
			((PtrValue)holder).update(src);
		}
	}
	
	@Override
	public int getSize() {
		return PtrType.cType.getSize();
	}
	
	

}
