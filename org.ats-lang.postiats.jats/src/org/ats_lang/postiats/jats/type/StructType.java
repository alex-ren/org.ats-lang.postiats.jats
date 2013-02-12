package org.ats_lang.postiats.jats.type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ats_lang.postiats.jats.value.ATSValue;
import org.ats_lang.postiats.jats.value.StructValue;

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
    public StructValue createDefault() {
        HashMap<String, ATSValue> map = new HashMap<String, ATSValue>();
        for (Pair p: m_members) {
            map.put(p.m_id, p.m_ty.createDefault());
        }
        return new StructValue(this, map);
    }
}
