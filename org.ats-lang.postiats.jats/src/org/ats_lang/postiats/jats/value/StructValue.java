package org.ats_lang.postiats.jats.value;

import java.util.HashMap;
import java.util.Map;

import org.ats_lang.postiats.jats.type.StructType;

public class StructValue implements ATSValue {
	Map<String, ATSValue> m_mem;
	StructType m_type;

	public StructValue(StructType type, Map<String, ATSValue> map) {
		m_type = type;
		m_mem = map;
	}
	
	public void updateItem(String id, ATSValue v) {
	    m_mem.get(id).copyfrom(v);
	}
	
	public ATSValue getItem(String id) {
	    return m_mem.get(id);
	}

	@Override
	public void copyfrom(ATSValue v) {
		if (v instanceof StructValue) {
			StructValue sv = (StructValue) v;
			Map<String, ATSValue> src_map = sv.getContent();

			for (Map.Entry<String, ATSValue> entry : m_mem.entrySet()) {
				entry.getValue().copyfrom(src_map.get(entry.getKey()));
			}
		} else {
			throw new Error("copy from non-structure value");
		}
	}

	@Override
	public Map<String, ATSValue> getContent() {
		return m_mem;
	}

    @Override
    public StructValue deepcopy() {
        Map<String, ATSValue> mem = new HashMap<String, ATSValue>();
        for (Map.Entry<String, ATSValue> entry: m_mem.entrySet()) {
            mem.put(entry.getKey(), entry.getValue().deepcopy());
        }
        
        return new StructValue(m_type, mem);
    }

    @Override
    public StructType getType() {
        return m_type;
    }
    
}
