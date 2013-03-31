package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.PrimValue;
import org.ats_lang.postiats.jats.value.SSizeValue;

public class SSizeType implements PrimType {

    public static final SSizeType cType = new SSizeType();
    
    public static final int m_size = 4;
    
    private SSizeType() {}
    
    public static SSizeValue fromString(String text) {
        return new SSizeValue(new Integer(text));
    }
    
    @Override
    public String toString() {
        return SSizeValue.class.getSimpleName();
    }
    
    @Override
    public int getSize() {
        return m_size;
    }

    @Override
    public SSizeValue createDefault() {
        return new SSizeValue(new Integer(0));
    }

    @Override
    public SSizeValue castFrom(PrimValue pv) {
        return SSizeValue.castFromV(pv);
    }

}