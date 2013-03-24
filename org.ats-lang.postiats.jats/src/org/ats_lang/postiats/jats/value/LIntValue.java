package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.LIntType;

public class LIntValue extends PrimValue {
    
    public static final LIntType m_type = LIntType.cType;

    public LIntValue(Integer v) {
        super(v);
    }
    
    public LIntValue(Object v) {
        super(v);
        if (!(v instanceof Integer)) {
            throw new Error ("LIntValue, type conversion not supported.");
        }
    }
    
    public static LIntValue create(int v) {
        return new LIntValue(v);
    }
    
    @Override
    public LIntValue castFrom(PrimValue pv) {
        return LIntValue.castFromV(pv);
    }
    
    public static LIntValue castFromV(PrimValue pv) {
        if (pv instanceof LIntValue) { 
            return new LIntValue(((LIntValue)pv).getContent());
        } else {
            throw new Error("LIntValue::castFrom");
        }
    }
    
    @Override
    public LIntValue deepcopy() {
        return new LIntValue((Integer)(this.getContent()));
    }

    @Override
    public Integer getContent() {
        return (Integer)super.m_mem;
    }
    
    
    @Override
    public LIntType getType() {
        return m_type;
    }
    
    
    
}
