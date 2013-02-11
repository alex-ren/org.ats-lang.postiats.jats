package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class StringType implements ATSType {

    public static final StringType cType = new StringType();
    
    private StringType() {}
    
    @Override
    public int getSize() {
        return 4;
    }

    public static ATSValue fromString(String text) {
        return new ATSValue(StringType.cType, text);
    }

	@Override
	public void deepcopy(ATSValue dest, ATSValue src) {
		dest.updateContent(src.getContent());		
	}
}
