package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class I0ntType implements ATSType {
    
    public static final I0ntType cType = new I0ntType();

    private I0ntType () {}
    
    @Override
    public int getSize() {
        return 4;
    }

    public static ATSValue fromString(String text) {
        return new ATSValue(I0ntType.cType, new Integer(text));
    }

	@Override
	public void deepcopy(ATSValue dest, ATSValue src) {
		dest.updateContent(src.getContent());		
	}
	
}
