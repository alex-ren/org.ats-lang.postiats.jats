package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class FloatType implements ATSType {
    
    public static final FloatType cType = new FloatType();

    private FloatType () {}
    
    @Override
    public int getSize() {
        return 8;
    }

    public static ATSValue fromString(String text) {
        return new ATSValue(FloatType.cType, new Float(text));
    }

	@Override
	public void deepcopy(ATSValue dest, ATSValue src) {
		dest.updateContent(src.getContent());		
	}
}
