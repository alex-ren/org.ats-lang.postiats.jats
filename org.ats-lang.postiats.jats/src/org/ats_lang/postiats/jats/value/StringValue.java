package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.StringType;

public class StringValue extends PrimValue {

    public static final StringType m_type = StringType.cType;

    public StringValue(String v) {
        super(v);
    }
    
    public StringValue(Object v) {
        super(v);
        if (!(v instanceof String)) {
            throw new Error ("StringValue, type conversion not supported.");
        }
    }
    
    public static StringValue create(String v) {
        return new StringValue(v);
    }
    
    @Override
    public StringValue castFrom(PrimValue pv) {
        return StringValue.castFromV(pv);
    }

    public static StringValue castFromV(PrimValue pv) {
        if (pv instanceof StringValue) { 
            return new StringValue(((StringValue)pv).getContent());
        } else {
            throw new Error("StringValue::castFrom");
        }
    }
    
    @Override
    public StringValue deepcopy() {
        return new StringValue((String)(this.getContent()));
    }

    @Override
    public String getContent() {
        return (String)super.m_mem;
    }

    @Override
    public StringType getType() {
        return m_type;
    }
    
    
}