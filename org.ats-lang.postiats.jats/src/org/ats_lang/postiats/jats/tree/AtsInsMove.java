package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.type.StructType;
import org.ats_lang.postiats.jats.type.VoidType;
import org.ats_lang.postiats.jats.utils.ATSScope;
import org.ats_lang.postiats.jats.value.Ptrk;
import org.ats_lang.postiats.jats.value.SingletonValue;

public class AtsInsMove extends ATSTypeNode {
    private String m_tmp;
    
 // type for m_tmp, may be RefType, may not be BoxedType or RefBoxedType
    private ATSType m_type;  
    private ATSNode m_val;

    public AtsInsMove(String tmp, ATSType ty, ATSNode val) {
    	super(VoidType.cType);
        m_tmp = tmp;
        m_type = ty;
        m_val = val;
    }

    @Override
    // #define ATSINSmove(tmp, val) (tmp = val)
    public SingletonValue evaluate(Map<String, ATSType> types,
            Map<String, FuncDef> funcs, ATSScope<Object> scope) {
		Object v = m_val.evaluate(types, funcs, scope);
		ATSType ty = m_val.getType();
		if (ty instanceof RefType) {
			// copy the content of v since it's a reference
			// v = ?
			RefType.cloneValue(v, ((RefType) ty).defType());
		}
		
		if (m_type instanceof RefType) {
			if (((RefType) m_type).defType() instanceof StructType) {
				// v := StructType
				// Since this is still initialization, I just use the new Map as the reference
				// and totally discard the already allocated one. (Just for simpler code.)
				scope.addValue(m_tmp, v);
			} else {
				// Since this is still initialization, I just use the new Ptrk as the reference
				// and totally discard the already allocated one. (Just for simpler code.)				
				scope.addValue(m_tmp, new Ptrk(v));
			}
		} else {
			scope.addValue(m_tmp, v);
		}
		
	    return SingletonValue.VOID;
    }
}
