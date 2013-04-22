package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsStoreFltrecOfs extends ATSTypeNode {
    private String m_tmp;  // name of the structure object
    private String m_lab;  // name of the member
    private ATSType m_ty;  // type of the object
    private ATSNode m_val;  // value of the member
    
    
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

    public AtsInsStoreFltrecOfs(ATSScope<ATSType> tyscope, String tmp, ATSType tyrec, String lab, ATSNode val) {
        super(VoidType.cType);
        m_tmp = tmp;
        m_ty = tyscope.getValue(m_tmp);
        if (m_ty instanceof RefType) {
        	ATSType ty = ((RefType)m_ty).defType();
        	if (ty.equals(tyrec)) {
        		throw new Error("Type mismatch");
        	}
        } else if (m_ty.equals(tyrec)) {
    		throw new Error("Type mismatch");
    	} else {}

        m_lab = lab;
        m_val = val;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
        Object v = m_val.evaluate(types, funcs, scope);
        
        Object rec = scope.getValue(m_tmp);
        if (rec instanceof Map<?, ?>) {
        	if (m_ty instanceof RefType) {
        		xx
        	} else {
        	    ((Map<String, Object>)rec).put(m_lab, v);
        	}
        } else {
        	throw new Error("non record");
        }
        
        return SingletonValue.VOID;        
    }

}
