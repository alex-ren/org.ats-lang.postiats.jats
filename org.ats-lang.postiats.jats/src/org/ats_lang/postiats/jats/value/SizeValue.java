package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.SizeType;

public class SizeValue extends PrimValue {

    public static final SizeType m_type = SizeType.cType;

    public SizeValue(Integer v) {
        super(v);
    }
    
    public SizeValue(Object v) {
        super(v);
        if (!(v instanceof Integer)) {
            throw new Error ("SizeValue, type conversion not supported.");
        }
    }
    
    @Override
    public SizeValue castFrom(PrimValue pv) {
        return SizeValue.castFromV(pv);
    }
    
    public static SizeValue castFromV(PrimValue pv) {
        if (pv instanceof SizeValue) { 
            return new SizeValue(((SizeValue)pv).getContent());
        } else if (pv instanceof IntValue){
            Integer v = ((IntValue)pv).getContent();
            if (v >= 0) {
                return new SizeValue(v);
            } else {
                throw new Error("SizeValue::castFrom negetive number");
            }
        } else {
            throw new Error("SizeValue::castFrom");
        }
    }
    
    @Override
    public SizeValue deepcopy() {
        return new SizeValue((Integer)(this.getContent()));
    }

    @Override
    public Integer getContent() {
        return (Integer)super.m_mem;
    }

    @Override
    public SizeType getType() {
        return m_type;
    }

}
