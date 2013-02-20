package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.SizeValue;

public class SizeType implements PrimType {

    public static final SizeType cType = new SizeType();
    
    private SizeType() {}
    
    public static SizeValue fromString(String text) {
        return new SizeValue(new Integer(text));
    }
    
    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public SizeValue createDefault() {
        return new SizeValue(new Integer(0));
    }

    @Override
    public SizeValue castFrom(PrimValue pv) {
        return SizeValue.castFromV(pv);
    }

}
