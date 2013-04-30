package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsStoreFltrecOfs extends ATSTypeNode {
    private String m_tmp;  // name of the structure object
    private String m_lab;  // name of the member
    private ATSType m_ty;  // type of the object
    private ATSNode m_val;  // value of the member
    private StructType m_tyrec;  // type of the structure
    
    
    // #define ATSINSstore_fltrec_ofs(tmp, tyrec, lab, val) ((tmp).lab = val)
    // example
//    typedef
//    struct {
//    atstkind_t0ype(atstype_int) atslab$0; 
//    atstkind_t0ype(atstype_int) atslab$1; 
//    } postiats_tyrec_0 ;
//    
//    ATStmpdec(tmp11, postiats_tyrec_0) ;
//    
//    ATSINSstore_fltrec_ofs (tmp11, postiats_tyrec_0, atslab$0, ATSPMVi0nt(0)) ;
    // tmpty = StructType or RefType(StructType)
    // tyrec = StructType
    public AtsInsStoreFltrecOfs(ATSType tmpty, String tmp, ATSType tyrec, String lab, ATSNode val) {
        super(VoidType.cType);
        m_tmp = tmp;
        m_lab = lab;
        m_ty = tmpty;
        m_val = val;
        if (tyrec instanceof StructType) {
            m_tyrec = (StructType) tyrec;
        } else {
            throw new Error("type mismatch");
        }
        
        // check for fun
        if (m_ty instanceof RefType) {
        	ATSType ty = ((RefType)m_ty).defType();
        	if (ty.equals(tyrec)) {
        	} else {
        	    throw new Error("Type mismatch");
        	}
        } else if (m_ty.equals(tyrec)) {
    	} else {
    	    throw new Error("Type mismatch");
    	}

        
        
    }

    @Override
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        Object v = m_val.evaluate(types, funcs, scope);
        ATSType vt = m_val.getType();
        
        Object rec = scope.getValue(m_tmp);
        
        if (rec instanceof Map<?, ?>) {
        	if (m_ty instanceof RefType) {
                ATSType ty = ((RefType) m_ty).defType();
        	    if (ty instanceof StructType) {
        	        ATSType dstType = ((StructType) ty).getMember(m_lab);
        	        if (dstType instanceof StructType) {
        	            // The member is a flat structure.
        	            @SuppressWarnings("unchecked")
                        Object dst = ((Map<String, Object>)rec).get(m_lab);
                        RefType.update(dst, dstType, v, vt);
        	        } else {  // The member is of PrimType.
                        if (vt instanceof RefType) {
                            v = RefType.cloneValue(v, vt);
                        }
                        @SuppressWarnings("unchecked")
                        Map<String, Object> mrec = (Map<String, Object>)rec;
                        mrec.put(m_lab, v);
        	        }

        	    } else {
        	        throw new Error("Type mismatch");
        	    }
        		
        	} else {
        	    if (vt instanceof RefType) {
        	        v = RefType.cloneValue(v, vt);
        	    }
        	    @SuppressWarnings("unchecked")
                Map<String, Object> mrec = (Map<String, Object>)rec;
                mrec.put(m_lab, v);
        	}
        } else {
        	throw new Error("non record");
        }
        
        return SingletonValue.VOID;        
    }

}
