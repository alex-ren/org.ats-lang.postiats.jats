package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.BoolType;
import org.ats_lang.postiats.jats.type.CharType;
import org.ats_lang.postiats.jats.type.IntType;

public class PrimValue implements ATSValue {
    
    private Object m_mem;
    private ATSType m_type;
    
    private PrimValue(ATSType type, Object mem) {
        m_type = type;
        m_mem = mem;
    }
    
    public PrimValue(int v) {
        this(IntType.cType, new Integer(v));
    }
    
    public PrimValue(boolean v) {
        this(BoolType.cType, v);
    }
    
    public PrimValue(Character v) {
        this(CharType.cType, v);
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
