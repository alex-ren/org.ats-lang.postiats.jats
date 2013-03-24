package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.LLIntType;

public class LLIntValue extends PrimValue {
    
    public static final LLIntType m_type = LLIntType.cType;

    public LLIntValue(Long v) {
        super(v);
    }
    
    public LLIntValue(Object v) {
        super(v);
        if (!(v instanceof Long)) {
            throw new Error ("LLIntValue, type conversion not supported.");
        }
    }
    
    public static LLIntValue create(int v) {
        return new LLIntValue(v);
    }
    
    @Override
    public LLIntValue castFrom(PrimValue pv) {
        return LLIntValue.castFromV(pv);
    }
    
    public static LLIntValue castFromV(PrimValue pv) {
        if (pv instanceof LLIntValue) { 
            return new LLIntValue(((LLIntValue)pv).getContent());
        } else {
            throw new Error("LLIntValue::castFrom");
        }
    }
    
    @Override
    public LLIntValue deepcopy() {
        return new LLIntValue((Long)(this.getContent()));
    }

    @Override
    public Long getContent() {
        return (Long)super.m_mem;
    }
    
    
    @Override
    public LLIntType getType() {
        return m_type;
    }
    
    
    
}
