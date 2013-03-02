package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.IntType;
import org.ats_lang.postiats.jats.type.PtrType;

public class PtrValue implements ATSValue {
    
    public static final PtrType m_type = PtrType.cType;
    
    private ATSValue m_mem;
    // It's possible that the pointer points to an element in an array.
    private ArrayValue m_arr;
    private int m_ind;

    // for pointer
    public PtrValue(ATSValue v) {
    	m_mem = v;
    	m_arr = null;
    	m_ind = 0;    	
    }
    
    public PtrValue(ArrayValue arr) {
        m_mem = arr.get(0);
        m_arr = arr;
        m_ind = 0;      
    }
    
    private PtrValue(ATSValue mem, ArrayValue arr, int ind) {
        m_mem = mem;
        m_arr = arr;
        m_ind = ind;
    }

    // v is actually an element of arr
    public PtrValue(ArrayValue arr, int ind) {
        m_arr = arr;
        m_ind = ind;
        m_mem = arr.get(ind);
    }
    
	public ATSValue deRef() {
		return m_mem;
	}

	public void incIndex() {
	    m_ind++;
	    m_mem = m_arr.get(m_ind);
	}
	
	public void addByteSize(int sz) {
	    int len = m_mem.getType().getSize();
	    if (sz % len != 0) {
	        throw new Error("PtrValue::addByteSize, ptr boundry error");
	    }
	    
	    m_ind += sz / len;
	    m_mem = m_arr.get(m_ind);
	    
	    
	}
	
	@Override
	public void copyfrom(ATSValue v) {
		if (v instanceof PtrValue) {
			m_mem = ((PtrValue) v).m_mem;
			m_arr = ((PtrValue) v).m_arr;
			m_ind = ((PtrValue) v).m_ind;
		} else {
    		throw new Error("PtrValue::copyfrom: copy from non-pointer value.");
    	}
	}

	@Override
	public ATSValue getContent() {
		return m_mem;
	}

    @Override
    public PtrValue deepcopy() {
        return new PtrValue(m_mem, m_arr, m_ind);
    }
    
    @Override
    public PtrType getType() {
        return m_type;
    }

}
