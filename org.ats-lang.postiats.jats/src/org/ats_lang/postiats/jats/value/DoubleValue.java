package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.DoubleType;

public class DoubleValue extends PrimValue {

    static final DoubleType m_type = DoubleType.cType;

    public DoubleValue(Double v) {
        super(v);
    }
    
    public DoubleValue(Object v) {
        super(v);
        if (!(v instanceof Double)) {
            throw new Error ("DoubleValue, type conversion not supported.");
        }
    }
    
    public static DoubleValue castFrom(PrimValue pv) {
        if (pv instanceof DoubleValue) { 
            return new DoubleValue(((DoubleValue)pv).getContent());
        } else {
            throw new Error("DoubleValue::castFrom");
        }
    }
    
    @Override
    public ATSValue deepcopy() {
        return new DoubleValue((Double)(this.getContent()));
    }

    @Override
    public Double getContent() {
        return (Double)super.m_mem;
    }

}
