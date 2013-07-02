package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.type.ATSType;

import org.ats_lang.postiats.jats.type.VoidType;


public class AtsInsMove extends ATSTypeNode {
	public String m_tmp;

	// type for m_tmp, may be RefType, may not be BoxedType or RefBoxedType
	public ATSType m_type;
	public ATSNode m_val;

	public AtsInsMove(ATSType ty, String tmp, ATSNode val) {
		super(VoidType.cType);
		m_tmp = tmp;
		m_type = ty;
		m_val = val;
	}

//	@Override
//	// #define ATSINSmove(tmp, val) (tmp = val)
//	public SingletonValue evaluate(Map<String, ATSType> types,
//	        Map<String, FuncDef> funcs, ATSScope<Object> scope) {
//		Object src = m_val.evaluate(types, funcs, scope);
//		ATSType srcty = m_val.getType();
//		if (m_type instanceof RefType) {
//			RefType.update(scope.getValue(m_tmp), src, srcty);
//		} else {
//			if (srcty instanceof RefType) {
//				scope.addValue(m_tmp,
//				        RefType.cloneValue(src, ((RefType) srcty).defType()));
//			} else {
//				scope.addValue(m_tmp, src);
//			}
//		}
//
//		return SingletonValue.VOID;
//	}
	

    @Override
    public Object accept(ATSTreeVisitor visitor) {
        return visitor.visit(this);
        
    }
    
    
    
}




