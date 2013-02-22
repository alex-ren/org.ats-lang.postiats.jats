package org.ats_lang.postiats.jats.interpreter;

import org.ats_lang.postiats.jats.type.ATSType;

public class FuncPara {
    
    static public enum ParaDecorator {REF0, REF1};
    
	private ATSType  m_type;
	private String   m_id;
	private ParaDecorator m_dec;
	
	public String getId() {
	    return m_id;
	}
	
	public FuncPara(ATSType type, String id) {
		m_type = type;
		m_id = id;
	}

}
