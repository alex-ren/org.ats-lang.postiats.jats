package org.ats_lang.postiats.jats.type;

import java.util.List;

import org.ats_lang.postiats.jats.interpreter.FuncPara;

public class FuncType implements ATSType {

	@Override
	public int getSize() {
		return PtrkType.cType.getSize();
	}

	@Override
	public Object createNormalDefault() {
		throw new Error("not supported");
	}

	@Override
	public Object createRefDefault() {
		throw new Error("not supported");
	}
	
	
	private ATSType m_rettype;
	private List<FuncPara> m_paralst;
	
	public FuncType(ATSType rettype, List<FuncPara> paralst) {
	    m_rettype = rettype;
	    m_paralst = paralst;
	    
	}
	
	public ATSType getReturnType() {
	    return m_rettype;
	}

}
