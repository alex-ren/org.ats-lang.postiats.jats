package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.FloatType;

public class FloatValue extends PrimValue {

    static final FloatType m_type = FloatType.cType;

    public FloatValue(Float v) {
        super(v);
    }
    
    public FloatValue(Object v) {
        super(v);
        if (!(v instanceof Float)) {
            throw new Error ("FloatValue, type conversion not supported.");
        }
    }
    
    public static FloatValue castFrom(PrimValue pv) {
        if (pv instanceof FloatValue) { 
            return new FloatValue(((FloatValue)pv).getContent());
        } else {
            throw new Error("FloatValue::castFrom");
        }
    }
    
    @Override
    public ATSValue deepcopy() {
        return new FloatValue((Float)(this.getContent()));
    }

    @Override
    public Float getContent() {
        return (Float)super.m_mem;
    }

}
