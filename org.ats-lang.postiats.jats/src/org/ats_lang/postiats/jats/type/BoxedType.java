package org.ats_lang.postiats.jats.type;

import java.util.HashMap;
import java.util.Map;

import org.ats_lang.postiats.jats.value.Ptrk;

public class BoxedType extends ATSReferableType {

	public static final BoxedType cType = new BoxedType();

	private BoxedType() {
		super(Decorator.TYPE);
	}

	@Override
	public int getSize() {
		return PtrkType.cType.getSize();
	}

	@Override
	public Map<String, Object> createNormalDefault() {
		return new HashMap<String, Object>();
	}

	@Override
	public boolean equals(ATSType ty) {
		if (this == ty || ty instanceof PtrkType) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Map<String, Object> cloneValue(Object src) {
		@SuppressWarnings("unchecked")
		Map<String, Object> dst = (Map<String, Object>) src;
		return dst;
	}

	// public static void checkupdateRefBoxedType(ATSType ty) {
	// // update type
	// if (ty instanceof RefType) {
	// ATSType innerty = ((RefType) ty).defType();
	// if (innerty instanceof BoxedType) {
	// } else if (innerty instanceof PtrkType) {
	// ((RefType) ty).updateType(BoxedType.cType);
	// } else {
	// System.out.println("innerty is " + innerty);
	// throw new Error("Type mismatch");
	// }
	// } else if (ty instanceof BoxedType) {
	// } else {
	// System.out.println("ty is " + ty);
	// throw new Error("Type mismatch");
	// }
	// }

	// @Override
	// public BoxedType createUpdatable(ATSType ty) {
	// BoxedType ret = new BoxedType(ty);
	// return ret;
	// }

}
