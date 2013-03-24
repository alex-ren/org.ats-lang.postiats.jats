package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.SSizeType;

public class SSizeValue extends PrimValue {

    public static final SSizeType m_type = SSizeType.cType;

    public SSizeValue(Integer v) {
        super(v);
    }
    
    public SSizeValue(Object v) {
        super(v);
        if (!(v instanceof Integer)) {
            throw new Error ("SSizeValue, type conversion not supported.");
        }
    }
    
    public static SSizeValue create(int v) {
        return new SSizeValue(v);
    }
    
    @Override
    public SSizeValue castFrom(PrimValue pv) {
        return SSizeValue.castFromV(pv);
    }
    
    public static SSizeValue castFromV(PrimValue pv) {
        if (pv instanceof SSizeValue) { 
            return new SSizeValue(((SSizeValue)pv).getContent());
        } else if (pv instanceof IntValue){
            return new SSizeValue(((IntValue)pv).getContent());
        } else {
            throw new Error("SSizeValue::castFrom");
        }
    }
    
    @Override
    public SSizeValue deepcopy() {
        return new SSizeValue((Integer)(this.getContent()));
    }

    @Override
    public Integer getContent() {
        return (Integer)super.m_mem;
    }

    @Override
    public SSizeType getType() {
        return m_type;
    }

}
