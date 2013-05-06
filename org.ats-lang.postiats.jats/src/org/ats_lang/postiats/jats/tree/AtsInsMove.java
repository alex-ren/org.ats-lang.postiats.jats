package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSReferableType;
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

	public AtsInsMove(ATSType ty, String tmp, ATSNode val) {
		super(VoidType.cType);
		m_tmp = tmp;
		m_type = ty;
		m_val = val;
	}

	@Override
	// #define ATSINSmove(tmp, val) (tmp = val)
	public SingletonValue evaluate(Map<String, ATSType> types,
	        Map<String, FuncDef> funcs, ATSScope<Object> scope) {
		Object src = m_val.evaluate(types, funcs, scope);
		ATSType srcty = m_val.getType();
		if (m_type instanceof RefType) {
			RefType.update(scope.getValue(m_tmp), src, srcty);
		} else {
			if (srcty instanceof RefType) {
				scope.addValue(m_tmp,
				        RefType.cloneValue(src, ((RefType) srcty).defType()));
			} else {
				scope.addValue(m_tmp, src);
			}
		}

		return SingletonValue.VOID;
	}
}
