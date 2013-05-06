package org.ats_lang.postiats.jats.type;

public class ATSTempType implements ATSType {
	
	protected ATSTempType() {		
	}

	@Override
	public int getSize() {
		throw new Error("not supported");
	}

	@Override
	public Object createNormalDefault() {
		throw new Error("not supported");
	}

	@Override
	public Object createRefDefault() {
		throw new Error("not supported");
	}

	@Override
	public boolean equals(ATSType ty) {
		throw new Error("not supported");
	}

}
