package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ULLIntType;

public class ULLIntValue extends PrimValue {
    
    public static final ULLIntType m_type = ULLIntType.cType;

    public ULLIntValue(Long v) {
        super(v);
    }
    
    public ULLIntValue(Object v) {
        super(v);
        if (!(v instanceof Long)) {
            throw new Error ("ULLIntValue, type conversion not supported.");
        }
    }
    
    public static ULLIntValue create(int v) {
        return new ULLIntValue(v);
    }
    
    @Override
    public ULLIntValue castFrom(PrimValue pv) {
        return ULLIntValue.castFromV(pv);
    }
    
    public static ULLIntValue castFromV(PrimValue pv) {
        if (pv instanceof ULLIntValue) { 
            return new ULLIntValue(((ULLIntValue)pv).getContent());
        } else {
            throw new Error("ULLIntValue::castFrom");
        }
    }
    
    @Override
    public ULLIntValue deepcopy() {
        return new ULLIntValue((Long)(this.getContent()));
    }

    @Override
    public Long getContent() {
        return (Long)super.m_mem;
    }
    
    
    @Override
    public ULLIntType getType() {
        return m_type;
    }
    
    
    
}
