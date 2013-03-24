package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.LDoubleType;

public class LDoubleValue extends PrimValue {

    public static final LDoubleType m_type = LDoubleType.cType;

    public LDoubleValue(Double v) {
        super(v);
    }
    
    public LDoubleValue(Object v) {
        super(v);
        if (!(v instanceof Double)) {
            throw new Error ("DoubleValue, type conversion not supported.");
        }
    }
    
    public static LDoubleValue create(Double v) {
        return new LDoubleValue(v);
    }
    
    @Override
    public LDoubleValue castFrom(PrimValue pv) {
        return LDoubleValue.castFromV(pv);
    }

    public static LDoubleValue castFromV(PrimValue pv) {
        if (pv instanceof LDoubleValue) { 
            return new LDoubleValue(((LDoubleValue)pv).getContent());
        } else {
            throw new Error("LDoubleValue::castFrom");
        }
    }
    
    @Override
    public LDoubleValue deepcopy() {
        return new LDoubleValue(this.getContent());
    }

    @Override
    public Double getContent() {
        return (Double)super.m_mem;
    }

    @Override
    public LDoubleType getType() {
        return m_type;
    }
    
}
