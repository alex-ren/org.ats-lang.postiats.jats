package org.ats_lang.postiats.jats.type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.value.ATSValue;

public class StructType implements ATSType {
    private List<Pair> m_members;
    
    public StructType() {
        m_members = new ArrayList<Pair>();
    }
    
    public void addMember(String id, ATSType ty) {
        m_members.add(new Pair(id, ty));
    }

    @Override
    public int getSize() {
        int accu = 0;
        for (Pair p: m_members) {
            accu += p.m_ty.getSize();            
        }
        return accu;
    }
    
    private class Pair {
        String m_id;
        ATSType m_ty;
        
        Pair(String id, ATSType ty) {
            m_id = id;
            m_ty = ty;
        }
    }

	@Override
	public void deepcopy(ATSValue dest, ATSValue src) {
		Map<String, ATSValue> src_map = (Map<String, ATSValue>)(src.getContent());
		Map<String, ATSValue> dest_map = (Map<String, ATSValue>)(dest.getContent());
		
		for (Pair p : m_members)
		{
			dest_map.get(p.m_id).copyfrom(src_map.get(p.m_id));
		}

	}
}
