package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.CharType;

public class CharValue extends PrimValue {

    static final CharType m_type = CharType.cType;

    public CharValue(Character v) {
        super(v);
    }
    
    public CharValue(Object v) {
        super(v);
        if (!(v instanceof Character)) {
            throw new Error ("CharValue, type conversion not supported.");
        }
    }
    
    public static CharValue castFrom(PrimValue pv) {
        if (pv instanceof CharValue) { 
            return new CharValue(((CharValue)pv).getContent());
        } else {
            throw new Error("CharValue::castFrom");
        }
    }
    
    @Override
    public ATSValue deepcopy() {
        return new CharValue((Character)(this.getContent()));
    }

    @Override
    public Character getContent() {
        return (Character)super.m_mem;
    }

}
