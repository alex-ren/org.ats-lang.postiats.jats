package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.CharType;
import org.ats_lang.postiats.jats.type.F0loatType;
import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.type.ULIntType;

public class PrimValue implements ATSValue {
    
    private Object m_mem;
    private ATSType m_type;
    
    public PrimValue(ATSType type, Object mem) {
        m_type = type;
        m_mem = mem;
    }
    
    public PrimValue(Integer v) {
        this(IntType.cType, v);
    }
    
    public PrimValue(boolean v) {
        this(BoolType.cType, v);
    }
    
    public PrimValue(Character v) {
        this(CharType.cType, v);
    }
    
    public PrimValue(Float v) {
        this(F0loatType.cType, v);
    }
    
    public PrimValue(String v) {
        this(StringType.cType, v);
    }
    
	@Override
	public void copyfrom(ATSValue v) {
		if (v instanceof PrimValue) {
			m_mem = v.getContent();
		} else {
    		throw new Error("PrimValue::copyfrom: copy from non-primitive value.");
    	}
    }

	@Override
	public Object getContent() {
		return m_mem;
	}

}
