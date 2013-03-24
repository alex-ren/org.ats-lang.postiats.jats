package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.UIntType;

public class UIntValue extends PrimValue {
    
    public static final UIntType m_type = UIntType.cType;

    public UIntValue(Integer v) {
        super(v);
    }
    
    public UIntValue(Object v) {
        super(v);
        if (!(v instanceof Integer)) {
            throw new Error ("UIntValue, type conversion not supported.");
        }
    }
    
    public static UIntValue create(int v) {
        return new UIntValue(v);
    }
    
    @Override
    public UIntValue castFrom(PrimValue pv) {
        return UIntValue.castFromV(pv);
    }
    
    public static UIntValue castFromV(PrimValue pv) {
        if (pv instanceof UIntValue) { 
            return new UIntValue(((UIntValue)pv).getContent());
        } else {
            throw new Error("UIntValue::castFrom");
        }
    }
    
    @Override
    public UIntValue deepcopy() {
        return new UIntValue((Integer)(this.getContent()));
    }

    @Override
    public Integer getContent() {
        return (Integer)super.m_mem;
    }
    
    
    @Override
    public UIntType getType() {
        return m_type;
    }
    
    
    
}
