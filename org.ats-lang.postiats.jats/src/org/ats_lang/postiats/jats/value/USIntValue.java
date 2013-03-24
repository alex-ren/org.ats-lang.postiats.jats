package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.USIntType;

public class USIntValue extends PrimValue {
    
    public static final USIntType m_type = USIntType.cType;

    public USIntValue(Integer v) {
        super(v);
    }
    
    public USIntValue(Object v) {
        super(v);
        if (!(v instanceof Integer)) {
            throw new Error ("USIntValue, type conversion not supported.");
        }
    }
    
    public static USIntValue create(int v) {
        return new USIntValue(v);
    }
    
    @Override
    public USIntValue castFrom(PrimValue pv) {
        return USIntValue.castFromV(pv);
    }
    
    public static USIntValue castFromV(PrimValue pv) {
        if (pv instanceof USIntValue) { 
            return new USIntValue(((USIntValue)pv).getContent());
        } else {
            throw new Error("USIntValue::castFrom");
        }
    }
    
    @Override
    public USIntValue deepcopy() {
        return new USIntValue((Integer)(this.getContent()));
    }

    @Override
    public Integer getContent() {
        return (Integer)super.m_mem;
    }
    
    
    @Override
    public USIntType getType() {
        return m_type;
    }
    
    
    
}
