package org.ats_lang.postiats.jats.value;

import org.ats_lang.postiats.jats.type.ATSEltType;
import org.ats_lang.postiats.jats.type.ATSReferableType;
import org.ats_lang.postiats.jats.type.ArrayType;
import org.ats_lang.postiats.jats.type.BoxedType;
import org.ats_lang.postiats.jats.type.StringType;
import org.ats_lang.postiats.jats.type.StructType;

/*
 * Ptrk contains no information of type. It's similar to void *.
 */
public class Ptrk {
    private int m_offset;
    private Mem m_content; // either ArrayElement or not
    private Location m_loc;

    private Ptrk(Mem obj, int offset) {
    	m_offset = offset;
        m_content = obj;
        m_loc = null;
    }
    
    public static Ptrk createPtrk(ATSReferableType ty, Object v) {
    	Mem content = new Mem(ty, v);
    	return new Ptrk(content, 0);    	
    }

    // return the element stored in the lvalue pointed to by this pointer
    // There is no copy.
    public Object getValue(ATSReferableType elety) {
    	m_loc = m_content.getLoc(m_offset, elety);
    	return m_loc.getValue();
    }
    
    
    public Object cloneValue(ATSReferableType elety) {
    	return elety.cloneValue(this.getValue(elety));
    }
    
    // update a member
    public void updateFltrecOfs(Object src, String memName, StructType recType) {
    	int shift = recType.calcOffset(memName);
//    	System.out.println("shift is " + shift);
    	Location loc = m_content.getLoc(m_offset + shift, recType.getMember(memName));
    	loc.update(src);
    }
    
    public Ptrk SelFltrecOfs(String memName, StructType recType) {
    	return new Ptrk(m_content, m_offset + recType.calcOffset(memName));
    }


    public Ptrk addByteSize(int len) {
    	return new Ptrk(m_content, m_offset + len);
    }

    // return the lvalue pointed to by the pointer
    public Object def() {
        return this;
    }

    // v := elety
    // update the content of the lvalue pointed to by this pointer
    // v is deep copied.
    public void update(Object v, ATSReferableType elety) {
    	m_loc = m_content.getLoc(m_offset, elety);
    	m_loc.update(v);
    }

    public int subIndex(Ptrk from) {
    	return m_offset - from.m_offset;
    }
    
    static public interface Location {
    	public void update(Object src);  // src is deep copied as long as it's not elementary.
    	public Object getValue();  // return value is not a copy, but the actual object.
    }
    
    static public class Mem {
    	Object m_mem;
    	ATSReferableType m_type;
    	
    	public Mem(ATSReferableType ty, Object mem) {
    		m_mem = mem;
    		m_type = ty;
    	}
    	
    	public Object get() {
    		return m_mem;
    	}
    	
    	// loctype may be equal to m_type
    	public Location getLoc(final int offset, ATSReferableType loctype) {
    		if (m_type instanceof ATSEltType) {
    			if (offset == 0) {
    				return new Location() {
						
						@Override
						public void update(Object src) {
							m_mem = src;							
						}
						@Override
						public Object getValue() {
							return m_mem;
						}
					};
    			} else {
    				throw new Error("Boundry error");
    			}
			} else if (m_type instanceof StringType) {
				if (m_type.equals(loctype)) {
					return new Location() {

						@Override
						public void update(Object src) {
							((StringType) m_type).copyFrom(m_mem, src);

						}

						@Override
						public Object getValue() {
							return m_mem;
						}
					};
				} else {
					return ((StringType) m_type).getLoc(m_mem, offset);
				}
			} else if (m_type instanceof ArrayType) {
				if (m_type.equals(loctype)) {
					return new Location() {

						@Override
						public void update(Object src) {
							((ArrayType) m_type).copyFrom(m_mem, src);

						}

						@Override
						public Object getValue() {
							return m_mem;
						}
					};
				} else {
					return ((ArrayType) m_type).getLoc(m_mem, offset, loctype);
				}
			} else if (m_type instanceof StructType) {
				if (m_type.equals(loctype)) {
					return new Location() {

						@Override
						public void update(Object src) {
							((StructType) m_type).copyFrom(m_mem, src);

						}

						@SuppressWarnings("unchecked")
						@Override
						public Object getValue() {
							return m_mem;
						}
					};
				} else {
					return ((StructType) m_type).getLoc(m_mem, offset, loctype);
				}
			} else {
				throw new Error("Type mismatch");
			}
		}
    	
    }
    
    public String createString() {
    	char[] buf = (char[]) m_content.get();
    	return StringType.createString(buf);
    	
    }
}
