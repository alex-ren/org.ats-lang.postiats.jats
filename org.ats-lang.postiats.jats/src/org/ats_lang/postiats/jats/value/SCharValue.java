package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.SCharType;

public class SCharValue extends PrimValue {

    public static final SCharType m_type = SCharType.cType;

    public SCharValue(Character v) {
        super(v);
    }
    
    public SCharValue(Object v) {
        super(v);
        if (!(v instanceof Character)) {
            throw new Error ("CharValue, type conversion not supported.");
        }
    }
    
    public static SCharValue create(Character v) {
        return new SCharValue(v);
    }
    
    @Override
    public SCharValue castFrom(PrimValue pv) {
        return SCharValue.castFromV(pv);
    }
    
    public static SCharValue castFromV(PrimValue pv) {
        if (pv instanceof SCharValue) { 
            return new SCharValue(((SCharValue)pv).getContent());
        } else {
            throw new Error("SCharValue::castFrom");
        }
    }
    
    
    
    @Override
    public SCharValue deepcopy() {
        return new SCharValue((Character)(this.getContent()));
    }

    @Override
    public Character getContent() {
        return (Character)super.m_mem;
    }

    @Override
    public SCharType getType() {
        return m_type;
    }
    
}
