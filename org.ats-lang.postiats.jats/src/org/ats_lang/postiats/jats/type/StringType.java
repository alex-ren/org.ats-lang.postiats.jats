package org.ats_lang.postiats.jats.type;

import org.ats_lang.postiats.jats.value.Ptrk.Location;


/*
 * This type is not currently used in the system.
 */

public class StringType extends ATSReferableType {
	int m_len;
	
	// len is the whole length including the null.
    public StringType(int len) {
        super(Decorator.T0YPE);
        m_len = len;
    }
    
    @Override
    public int getSize() {
        return m_len;
    }
    
    @Override
    public char[] createNormalDefault() {
    	char[] content = new char[m_len];
        for (int i = 0; i < m_len; ++i) {
        	content[i] = Character.MIN_VALUE;
        }
        content[m_len - 1] = Character.MIN_VALUE;
        return content;
    }
    
	@Override
    public boolean equals(ATSType ty) {
	    if (this == ty) {
	    	return true;
	    } else if (ty instanceof StringType) {
	    	StringType strty = (StringType) ty;
	    	if (m_len == strty.m_len) {
	    		return true;
	    	} else {
	    		return false;
	    	}
	    } else {
	    	return false;
	    }
    }

	@Override
    public char[] cloneValue(Object src) {
		if (src instanceof char[]) {
			char[] srcarr = (char[]) src;
			
			char[] dst = new char[m_len];
	        for (int i = 0; i < m_len; ++i) {
	        	dst[i] = srcarr[i];
	        }
	        return dst;
		} else {
			throw new Error("Type mismatch");
		}

    }
	
    public void copyFrom(Object dst, Object src) {
    	char [] sdst = (char[])dst;
    	char [] ssrc = (char[])src;
    	for (int i = 0; i < ssrc.length; ++i) {
    		sdst[i] = ssrc[i];
    	}
    }
    
	class StringLocation implements Location {
		char[] m_content;
		int m_offset;
		
		public StringLocation(Object src, int offset) {
			m_content = (char[]) src;
			m_offset = offset;
		}
		
		@Override
		public void update(Object src) {
			m_content[m_offset] = (Character)src;
			
		}
		
		@Override
		public Character getValue() {
			return m_content[m_offset];
		}
	};
	
	public Location getLoc(Object content, int offset) {
        return new StringLocation(content, offset);
	}
	
//    public static final StringType cType = new StringType(Decorator.TYPE); 
    
//    private StringType(Decorator dec) {
//        super(dec);
//    }
    
//    public static final int m_size = PtrType.m_size;
    
//    @Override
//    public String toString() {
//        return StringValue.class.getSimpleName();
//    }
//    
//    
//    @Override
//    public int getSize() {
//        throw new Error("not supported");
//    }
//
//    @Override
//    public Object createNormalDefault() {
//        throw new Error("not supported");
//    }
//
//    @Override
//    public Object createRefDefault() {
//        // TODO Auto-generated method stub
//        throw new Error("not supported");
//    }
//
//    public static Ptrk fromString(String text) {
//        char[] str = new char[text.length() + 1];
//    	for (int i = 0; i < text.length(); ++i) {
//    		str[i] = text.charAt(i);
//    	}
//    	str[str.length - 1] = Character.MIN_VALUE;  // '\u0000'
//    	StringElement strele = new StringElement(str, 0);
//        return new Ptrk(strele);
//    }
    
    public static String toString(char[] buf) {
        return new String(buf, 0, buf.length - 1);
    }
    
    public static String toString(char[] buf, int start, int len) {
        return new String(buf, start, len);
    }
    
//
//    public static String toString(Ptrk msg) {
//        return msg.formString();
//    }
//
//    @Override
//    public StringValue createDefault() {
//        return new StringValue("");
//    }

//    private StringType(Decorator dec) {
//        super(dec);
//    }

}
