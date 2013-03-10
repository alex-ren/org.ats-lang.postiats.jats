package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ULIntType;

public class ULIntValue extends PrimValue {
    
    public static final ULIntType m_type = ULIntType.cType;

    public ULIntValue(Integer v) {
        super(v);
    }
    
    public ULIntValue(Object v) {
        super(v);
        if (!(v instanceof Integer)) {
            throw new Error ("IntValue, type conversion not supported.");
        }
    }
    
    public static ULIntValue create(int v) {
        return new ULIntValue(v);
    }
    
    @Override
    public ULIntValue castFrom(PrimValue pv) {
        return ULIntValue.castFromV(pv);
    }

    public static ULIntValue castFromV(PrimValue pv) {
        if (pv instanceof ULIntValue) { 
            return new ULIntValue(((ULIntValue)pv).getContent());
        } else {
            throw new Error("ULIntValue::castFrom");
        }
    }
    
    @Override
    public ULIntValue deepcopy() {
        return new ULIntValue((Integer)(this.getContent()));
    }

    @Override
    public Integer getContent() {
        return (Integer)super.m_mem;
    }
    
    @Override
    public ULIntType getType() {
        return m_type;
    }
    
    
    
}
