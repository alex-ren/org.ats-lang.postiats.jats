package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.UCharType;

public class UCharValue extends PrimValue {

    public static final UCharType m_type = UCharType.cType;

    public UCharValue(Character v) {
        super(v);
    }
    
    public UCharValue(Object v) {
        super(v);
        if (!(v instanceof Character)) {
            throw new Error ("UCharValue, type conversion not supported.");
        }
    }
    
    public static UCharValue create(Character v) {
        return new UCharValue(v);
    }
    
    @Override
    public UCharValue castFrom(PrimValue pv) {
        return UCharValue.castFromV(pv);
    }
    
    public static UCharValue castFromV(PrimValue pv) {
        if (pv instanceof UCharValue) { 
            return new UCharValue(((UCharValue)pv).getContent());
        } else {
            throw new Error("UCharValue::castFrom");
        }
    }
    
    @Override
    public UCharValue deepcopy() {
        return new UCharValue((Character)(this.getContent()));
    }

    @Override
    public Character getContent() {
        return (Character)super.m_mem;
    }

    @Override
    public UCharType getType() {
        return m_type;
    }
    
}
