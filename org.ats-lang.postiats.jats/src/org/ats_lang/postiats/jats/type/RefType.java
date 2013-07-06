package org.ats_lang.postiats.jats.type;

import java.util.Map;

import org.ats_lang.postiats.jats.value.Ptrk;



public class RefType implements ATSType {
	private ATSReferableType m_type;
	
	public RefType(ATSReferableType type) {
		m_type = type;
	}
		
	@Override
	public int getSize() {
	    throw new Error("getSize not supported for RefType");
	}

    @Override
    public Ptrk createNormalDefault() {
    	return m_type.createRefDefault();
    }
    
    @Override
    public Ptrk createRefDefault() {
    	throw new Error("Ref of ref is unsupported");
    }

    @Override
    public boolean equals(ATSType ty) {
    	if (this == ty) {
    		return true;
    	}
    	
        if (ty instanceof RefType) {
            return m_type.equals(((RefType) ty).defType());
        } else {
            return false;
        }
    }

	public ATSReferableType defType() {
		return m_type;
	}
	
	public void updateType(ATSReferableType ty) {
	    m_type = ty;
	}
	
	// refobj := RefType
	// x := RefType (a) => x : Ptr
	static public Ptrk ptrof(Ptrk refobj) {
	    return refobj;
	}
	
  	// dst: Ptrk
	// srcType = dstType or srcType = RefType(dstType)
	static public void update(Object dst, Object src, ATSType srcType) {
	    if (dst instanceof Ptrk) {
	        // dstType = IntType or dstType = PtrType or dstType = BoxedType
	        if (srcType instanceof RefType) {
	            ATSReferableType realtype = ((RefType) srcType).defType();
	        	((Ptrk) dst).update(RefType.getValue(src, realtype), realtype);
	        } else if (srcType instanceof ATSReferableType) {
	            // srcType = IntType or or srcType = PtrType or srcType = BoxedType (StructType)
	            ((Ptrk)dst).update(src, (ATSReferableType)srcType);
	        } else {
	        	throw new Error("Wrong Type.");
	        }
	    } else {
	        throw new Error("Wrong Type.");
	    }
	}
	
	// v := RefType (ty)
	// v : Ptrk
	// Clone the value stored by the reference if necessary
    static public Object cloneValue(Object v, ATSReferableType ty) {
		if (v instanceof Ptrk) {
			return ((Ptrk) v).cloneValue(ty);
		} else {
			throw new Error("type mismatch");
		}
	}
    
	// v := RefType (ty)
	// v : Ptrk
    // There is no copy.
    static public Object getValue(Object v, ATSReferableType ty) {
		if (v instanceof Ptrk) {
			return ((Ptrk) v).getValue(ty);
		} else {
			throw new Error("type mismatch");
		}
    }
    
    static public Object deref(Object v) {
        return v;
    }
    
    // dst := RefType(recType)
    static public void updateFltrecOfs(Object dst, Object src, String memName, StructType recType) {
    	if (dst instanceof Ptrk) {
    		((Ptrk) dst).updateFltrecOfs(src, memName, recType);
    	} else {
    		throw new Error("type mismatch");
    	}
    }
    
    // dst := RefType(recType)
    static public Ptrk SelFltrecOfs(Object dst, String memName, StructType recType) {
    	if (dst instanceof Ptrk) {
    		return ((Ptrk) dst).SelFltrecOfs(memName, recType);
    	} else {
    		throw new Error("type mismatch");
    	}
    }
    
    public String toString() {
        return Ptrk.class.getSimpleName();
    }

    @Override
    public Object accept(ATSTypeVisitor visitor) {
        return visitor.visit(this);
    }

}
