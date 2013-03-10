package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.IntType;

public class IntValue extends PrimValue {
    
    public static final IntType m_type = IntType.cType;

    public IntValue(Integer v) {
        super(v);
    }
    
    public IntValue(Object v) {
        super(v);
        if (!(v instanceof Integer)) {
            throw new Error ("IntValue, type conversion not supported.");
        }
    }
    
    public static IntValue create(int v) {
        return new IntValue(v);
    }
    
    @Override
    public IntValue castFrom(PrimValue pv) {
        return IntValue.castFromV(pv);
    }
    
    public static IntValue castFromV(PrimValue pv) {
        if (pv instanceof IntValue) { 
            return new IntValue(((IntValue)pv).getContent());
        } else {
            throw new Error("IntValue::castFrom");
        }
    }
    
    @Override
    public IntValue deepcopy() {
        return new IntValue((Integer)(this.getContent()));
    }

    @Override
    public Integer getContent() {
        return (Integer)super.m_mem;
    }
    
    
    @Override
    public IntType getType() {
        return m_type;
    }
    
    
    
}
