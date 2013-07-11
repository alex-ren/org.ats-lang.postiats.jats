package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk;

public abstract class ATSTempType implements ATSType {
	
	protected ATSTempType() {		
	}

	@Override
	public int getSize() {
		throw new Error("not supported");
	}
//
//	@Override
//	public Object createNormalDefault() {
//		throw new Error("not supported");
//	}

	@Override
	public Ptrk createRefDefault() {
		throw new Error("not supported");
	}

	@Override
	public boolean equals(ATSType ty) {
	    return this == ty;
	}

}
