package org.ats_lang.postiats.jats.tree;


import org.ats_lang.postiats.jats.type.ATSType;

public abstract class ATSTypeNode implements ATSNode {
	private ATSType m_ty;
	
	public ATSTypeNode(ATSType ty) {
		m_ty = ty;
	}
	
	@Override
	public ATSType getType() {
		return m_ty;
	}
	
	public void updateType(ATSType ty) {
	    m_ty = ty;
	}

}
