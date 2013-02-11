package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.ATSValue;

public class IntType implements ATSType {
    
    public static final IntType cType = new IntType();
    
    private IntType() {}
    
    public static ATSValue fromString(String text) {
        return new ATSValue(IntType.cType, new Integer(text));
    }
    
    @Override
    public int getSize() {
        return 4;
    }

	@Override
	public void deepcopy(ATSValue dest, ATSValue src) {
		dest.updateContent(src.getContent());		
	}
}
