package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.BoolType;

public class BoolValue extends PrimValue {

    public static final BoolType m_type = BoolType.cType;

    public BoolValue(Boolean v) {
        super(v);
    }
    
    public BoolValue(Object v) {
        super(v);
        if (!(v instanceof Boolean)) {
            throw new Error ("BoolValue, type conversion not supported.");
        }
    }

    public static boolean isTrue(ATSValue v) {
        Object b = v.getContent();
        if (b instanceof Boolean) {
            return (Boolean) b == true;
        } else {
            return false;
        }
    }
    
    @Override
    public BoolValue castFrom(PrimValue pv) {
        return BoolValue.castFromV(pv);
    }
    
    public static BoolValue castFromV(PrimValue pv) {
        if (pv instanceof BoolValue) { 
            return new BoolValue(((BoolValue)pv).getContent());
        } else {
            throw new Error("BoolValue::castFrom");
        }
    }

    
    @Override
    public BoolValue deepcopy() {
        return new BoolValue((Boolean)(this.getContent()));
    }
    
    @Override
    public Boolean getContent() {
        return (Boolean)super.m_mem;
    }

    @Override
    public BoolType getType() {
        return m_type;
    }
    
}
