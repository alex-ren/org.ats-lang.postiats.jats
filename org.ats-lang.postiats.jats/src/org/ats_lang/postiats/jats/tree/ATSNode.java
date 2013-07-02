package org.ats_lang.postiats.jats.tree;

import org.ats_lang.postiats.jats.type.ATSType;

public interface ATSNode {
    public enum FunDecorator {STATIC, GLOBAL};
//	public Object evaluate(Map<String, ATSType> types, Map<String, FuncDef> funcs, ATSScope<Object> scope);
	
	public ATSType getType();
	
	public Object accept(ATSTreeVisitor visitor);
//	
//	public static class ValueType {
//		private ATSType m_ty;
//		private Object  m_val;
//		
//		public ValueType(ATSType ty, Object val) {
//			m_ty = ty;
//			m_val = val;
//		}
//		
//		public ATSType getType() {
//			return m_ty;
//		}
//		
//		public Object getValue() {
//			return m_val;
//		}
//	}
}
