package jats.utfpl.stfpl.csharpins;

import java.util.HashMap;
import java.util.Map;

import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.dynexp3.Cd3var;

public class CSVar {
	public Cd3var m_var;
	private ICSType m_type;
	
	private static Map<Cd3var, CSVar> s_map = new HashMap<Cd3var, CSVar>();
	

	public static CSVar fromCd3var(Cd3var var, ICSType type) {
		CSVar csvar = s_map.get(var);
		if (null != csvar) {
			return csvar;
		} else {
			csvar = new CSVar(var, type);
			s_map.put(var, csvar);
			return csvar;
		}
	}
	
	private CSVar(Cd3var var, ICSType type) {
		m_var = var;
		m_type = type;
	}

}
