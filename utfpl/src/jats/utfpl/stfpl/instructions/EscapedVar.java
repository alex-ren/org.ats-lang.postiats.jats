package jats.utfpl.stfpl.instructions;

import jats.utfpl.stfpl.dynexp3.Cd3var;

public class EscapedVar {
	static public enum EEscType {
		normal,
		from_env
	}
	
	public Cd3var m_var;
	public EEscType m_esc_type;
	
	public EscapedVar(Cd3var var, EEscType esc_type) {
	 
	    m_var = var;
	    m_esc_type = esc_type;
	}
	

}
