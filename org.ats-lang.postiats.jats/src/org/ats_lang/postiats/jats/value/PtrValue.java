package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.PtrType;

public class PtrValue implements ATSValue {
    
    public static final PtrType m_type = PtrType.cType;
    
    public static final PtrValue c_stdout = new PtrValue(new ATSValue[1]);
    public static final PtrValue c_stderr = new PtrValue(new ATSValue[1]);
    
    // It's possible that the array only has one element.
    private ATSValue[] m_arr;
    private int m_ind;

    public boolean isEqual(PtrValue p) {
        return m_arr == p.m_arr;
    }
    
    public PtrValue() {
        m_arr = null;
        m_ind = -1;
    }
    
    // for pointer
    public PtrValue(ATSValue v) {
    	m_arr = new ATSValue[] {v};
    	m_ind = 0;
    }
    
    public PtrValue(ATSValue[] arr) {
        m_arr = arr;
        m_ind = 0;      
    }

    // v is actually an element of arr
    public PtrValue(ATSValue[] arr, int ind) {
        m_arr = arr;
        m_ind = ind;
    }
    
	public ATSValue deRef(ATSType type) {
	    ATSValue mem = m_arr[m_ind];
	    if (type != mem.getType()) {
	        System.out.println("need " + mem.getType() + ", we get " + type);
	        throw new Error("deref on wrong type");
	    }
//	    System.out.println("type is " + type);
		return mem;
	}
	
    public ATSValue deRef(int index) {
        return m_arr[index];
    }
    

	public void incIndex() {
	    m_ind++;
	}
	
	public void addByteSize(int sz) {
	    ATSValue mem = m_arr[m_ind];
	    int len = mem.getType().getSize();
	    if (sz % len != 0) {
	        throw new Error("PtrValue::addByteSize, ptr boundry error");
	    }
	    
	    m_ind += sz / len;
	}
	
	public int subIndex(PtrValue p) {
	    if (this.m_arr != p.m_arr) {
	        throw new Error("subtraction between two unrelated pointers");
	    }
	    
	    return m_ind - p.m_ind;
	}
	
	public StringValue toStringValue() {
	    return new StringValue((CharValue[])m_arr);
	}
	
	@Override
	public void copyfrom(ATSValue v) {
		if (v instanceof PtrValue) {
			m_arr = ((PtrValue) v).m_arr;
			m_ind = ((PtrValue) v).m_ind;
		} else {
    		throw new Error("PtrValue::copyfrom: copy from non-pointer value.");
    	}
	}

	@Override
	public ATSValue getContent() {
		throw new Error("not supported");
	}

    @Override
    public PtrValue deepcopy() {
        return new PtrValue(m_arr, m_ind);
    }
    
    @Override
    public PtrType getType() {
        return m_type;
    }

}
