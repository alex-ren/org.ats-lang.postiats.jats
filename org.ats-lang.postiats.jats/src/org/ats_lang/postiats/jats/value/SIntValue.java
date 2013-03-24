package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.SIntType;

public class SIntValue extends PrimValue {
    
    public static final SIntType m_type = SIntType.cType;

    public SIntValue(Integer v) {
        super(v);
    }
    
    public SIntValue(Object v) {
        super(v);
        if (!(v instanceof Integer)) {
            throw new Error ("SIntValue, type conversion not supported.");
        }
    }
    
    public static SIntValue create(int v) {
        return new SIntValue(v);
    }
    
    @Override
    public SIntValue castFrom(PrimValue pv) {
        return SIntValue.castFromV(pv);
    }
    
    public static SIntValue castFromV(PrimValue pv) {
        if (pv instanceof SIntValue) { 
            return new SIntValue(((SIntValue)pv).getContent());
        } else {
            throw new Error("SIntValue::castFrom");
        }
    }
    
    @Override
    public SIntValue deepcopy() {
        return new SIntValue((Integer)(this.getContent()));
    }

    @Override
    public Integer getContent() {
        return (Integer)super.m_mem;
    }
    
    
    @Override
    public SIntType getType() {
        return m_type;
    }
    
    
    
}
