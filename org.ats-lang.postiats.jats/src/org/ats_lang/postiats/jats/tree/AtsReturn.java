package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.type.ATSType;
import org.ats_lang.postiats.jats.type.RefType;
import org.ats_lang.postiats.jats.utils.ATSScope;

public class AtsReturn extends ATSTypeNode {
	private ATSNode m_exp;
	private ATSType m_nodety;

	public AtsReturn(ATSNode exp) {
		super(exp.getType());
		m_nodety = exp.getType();
		if (m_nodety instanceof RefType) {
			this.updateType(((RefType) m_nodety).defType());
		}

		m_exp = exp;
	}

	// #define ATSreturn(x) return (x)
	// sample
	// ATSreturn(tmp21$1) ;
	@Override
	public AtsReturn.ReturnValue evaluate(Map<String, ATSType> types,
	        Map<String, FuncDef> funcs, ATSScope<Object> scope) {

		// Make the copy here since functions return value.
		Object v = m_exp.evaluate(types, funcs, scope);
		if (m_nodety instanceof RefType) {
			return new ReturnValue(RefType.cloneValue(v, ((RefType) m_nodety).defType()));
		} else {
			return new ReturnValue(v);
		}
	}

	static public class ReturnValue {
	    private Object m_v;
	    
	    public ReturnValue(Object v) {
	        m_v = v;
	    }
	    public Object getValue() {
	    	return m_v;
	    }
	}
}
