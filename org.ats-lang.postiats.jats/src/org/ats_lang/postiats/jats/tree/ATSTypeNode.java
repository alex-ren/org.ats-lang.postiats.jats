package org.ats_lang.postiats.jats.tree;

import java.util.Map;

import org.ats_lang.postiats.jats.interpreter.FuncDef;
import org.ats_lang.postiats.jats.interpreter.LValueScope;
import org.ats_lang.postiats.jats.type.ATSType;

public abstract class ATSTypeNode implements ATSNode {
	private ATSType m_ty;
	
	public ATSTypeNode(ATSType ty) {
		m_ty = ty;
	}
	
	public ATSType getType() {
		return m_ty;
	}

}
