package jats.utfpl.tree;

import jats.utfpl.utfpl.Cloc_t;

public class Location {
	private String m_desc;
	public Location(String desc) {
		m_desc = desc;
	}
	
	public Location(Cloc_t loc) {
		if (null != loc) {
			m_desc = loc.m_str;
		} else {
			m_desc = null;
		}
		
	}
	
	public String getDesc() {
		return m_desc;
	}

}
