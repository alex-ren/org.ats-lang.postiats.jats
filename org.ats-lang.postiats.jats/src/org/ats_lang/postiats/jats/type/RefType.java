package org.ats_lang.postiats.jats.type;

import java.util.Map;

import org.ats_lang.postiats.jats.value.Ptrk;



public class RefType implements ATSType {
	private ATSType m_type;
	
	public RefType(ATSType type) {
		m_type = type;
	}

	public ATSType defType() {
		return m_type;
	}
	
	
	// dst: Map, ATSPtr
	static public void update(Object dst, RefType dstType, Object src, ATSType srcType) {
	    if (dst instanceof Ptrk) {
	        // dstType = RefType (IntType) or dstType = RefType (PtrType) or dstType = RefType (BoxedType (StructType))
	        if (srcType instanceof RefType) {
	            // srcType = RefType (IntType) or srcType = RefType (PtrType) or srcType = RefType (BoxedType (StructType))
	            ((Ptrk)dst).update(((Ptrk)src).getValue());
	        } else {
	            // srcType = IntType or or srcType = PtrType or srcType = BoxedType (StructType)
	            ((Ptrk)dst).update(src);
	        }
	    } else if (dst instanceof Map<?,?>) {
	        // dstType = RefType (StructType)
	        // Therefore, srcType = StructType or srcType = RefType (StructType)
            @SuppressWarnings("unchecked")
            Map<String, Object> mdst = (Map<String, Object>) dst;
            @SuppressWarnings("unchecked")
            Map<String, Object> msrc = (Map<String, Object>) src;
	        
            StructType.update(mdst, msrc, (StructType)dstType.defType());
	    } else {
	        throw new Error("Wrong Type.");
	    }
	}
	
	// v := RefType
	// =>
	// v : Map or v : Ptr
	static public Object cloneValue(Object v, ATSType ty) {
		if (ty instanceof StructType) {
			return StructType.cloneValue(v, (StructType)ty);
		} else {
			if (v instanceof Ptrk) {
				return ((Ptrk) v).getValue();
			} else {
				throw new Error("type mismatch");
			}
		}
	}
	
	@Override
	public int getSize() {
	    throw new Error("getSize not supported for RefType");
	}

    @Override
    public Object createNormalDefault() {
    	return m_type.createRefDefault();
    }
    
    @Override
    public Object createRefDefault() {
    	throw new Error("Ref of ref is unsupported");
    }
    
	

}
