package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk.Location;

public class ArrayType extends ATSReferableType {
    private int m_len;
    private ATSReferableType m_elety;
    
    public ArrayType(ATSReferableType elety, int len) {
        super(Decorator.T0YPE);
        m_len = len;
        m_elety = elety;
    }
//
//    @Override
//    public String toString() {
//        return ArrayValue.class.getSimpleName();
//    }
//    
    @Override
    public int getSize() {
        return m_len * m_elety.getSize();
    }

    
    @Override
    public Object[] createNormalDefault() {
        Object[] content = new Object[m_len];
        for (int i = 0; i < m_len; ++i) {
        	content[i] = m_elety.createNormalDefault();
        }
        return content;
    }
    
	@Override
    public boolean equals(ATSType ty) {
	    if (this == ty) {
	    	return true;
	    } else if (ty instanceof ArrayType) {
	    	ArrayType arrty = (ArrayType) ty;
	    	if (m_len == arrty.m_len && m_elety.equals(arrty.m_elety)) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    } else {
	    	return false;
	    }
    }
	@Override
    public Object[] cloneValue(Object src) {
		if (src instanceof Object[]) {
			Object[] srcarr = (Object[]) src;
			
	        Object[] dst = new Object[m_len];
	        for (int i = 0; i < m_len; ++i) {
	        	dst[i] = m_elety.cloneValue(srcarr[i]);
	        }
	        return dst;
		} else {
			throw new Error("Type mismatch");
		}

    }
	
    public void copyFrom(Object dst, Object src) {
    	Object [] sdst = (Object[])dst;
    	Object [] ssrc = (Object[])src;
    	for (int i = 0; i < ssrc.length; ++i) {
			if (m_elety instanceof ATSEltType) {
				sdst[i] = ssrc[i];
			} else if (m_elety instanceof StringType) {
				((StringType)m_elety).copyFrom(sdst[i], ssrc[i]);
			} else if (m_elety instanceof ArrayType) {
				((ArrayType)m_elety).copyFrom(sdst[i], ssrc[i]);				
			} else if (m_elety instanceof StructType) {
				((StructType)m_elety).copyFrom(sdst[i], ssrc[i]);
			} else {
				throw new Error("type mismatch");
			}
    	}
    }
    
//	public ATSReferableType getEleType() {
//		return m_elety;
//	}
    
	class ArrayElementLocation implements Location {
		Object[] m_content;
		int m_loc;
		ATSReferableType m_elety;
		
		public ArrayElementLocation(Object[] content, int loc, ATSReferableType elety) {
	        m_content = content;
	        m_loc = loc;
	        m_elety = elety;
        }

		@Override
		public void update(Object src) {
			if (m_elety instanceof ATSEltType) {
				m_content[m_loc] = src;
			} else if (m_elety instanceof StringType) {
				((StringType)m_elety).copyFrom(m_content[m_loc], src);
			} else if (m_elety instanceof ArrayType) {
				((ArrayType)m_elety).copyFrom(m_content[m_loc], src);				
			} else if (m_elety instanceof StructType) {
				((StructType)m_elety).copyFrom(m_content[m_loc], src);
			} else {
				throw new Error("type mismatch");
			}
		}

		@Override
		public Object getValue() {
			return m_content[m_loc];
		}

	}
    // m_elety may be equal to loctype
	public Location getLoc(Object content, int offset, ATSReferableType loctype) {
		Object[] arrsrc = (Object []) content;
		int sz = m_elety.getSize();
		int q = offset / sz;
		int r = offset % sz;
		
		if (m_elety.equals(loctype)) {
			return new ArrayElementLocation(arrsrc, q, m_elety);
		}
		
		if (m_elety instanceof ATSEltType) {
			throw new Error("type mismatch");
		} else if (m_elety instanceof StringType) {
				return ((StringType) m_elety).getLoc(arrsrc[q], r);
		} else if (m_elety instanceof ArrayType) {
				return ((ArrayType) m_elety).getLoc(arrsrc[q], r, loctype);
		} else if (m_elety instanceof StructType) {
				return ((StructType) m_elety).getLoc(arrsrc[q], r, loctype);
		} else {
			throw new Error("Type mismatch");
		}
	}

}
