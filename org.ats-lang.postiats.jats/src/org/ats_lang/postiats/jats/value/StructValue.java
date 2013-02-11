package org.ats_lang.postiats.jats.value;

import java.util.HashMap;
import java.util.Map;

import org.ats_lang.postiats.jats.type.StructType;

public class StructValue implements ATSValue {
	HashMap<String, ATSValue> m_mem;
	StructType m_type;

	StructValue(StructType type) {
		m_type = type;
		m_mem = m_type.createDefault();
	}

	@Override
	public void copyfrom(ATSValue v) {
		if (v instanceof StructValue) {
			StructValue sv = (StructValue) v;
			HashMap<String, ATSValue> src_map = sv.getContent();

			for (Map.Entry<String, ATSValue> entry : m_mem.entrySet()) {
				entry.getValue().copyfrom(src_map.get(entry.getKey()));
			}
		} else {
			throw new Error("copy from non-structure value");
		}
	}

	@Override
	public HashMap<String, ATSValue> getContent() {
		return m_mem;
	}

}
