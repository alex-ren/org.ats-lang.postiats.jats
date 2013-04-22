package org.ats_lang.postiats.jats.type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ats_lang.postiats.jats.value.ATSValue;

public class StructType extends ATSKindType {
    private List<Pair> m_members;
    private String m_name;  // This name is for generating code.
    
    public StructType(String name) {
        super(Decorator.TYPE);
        m_members = new ArrayList<Pair>();
        m_name = name;
    }
    
    public void addMember(String id, ATSType ty) {
        m_members.add(new Pair(id, ty));
    }
    
    public ATSType getMember(String id) {
        for (Pair mem: m_members) {
            if (mem.m_id.equals(id)) {
                return mem.m_ty;
            }
        }
        throw new Error("member not found");
    }

//    @Override
//    public String toString() {
//        return m_name;
//    }
    
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

//    @Override
//    public StructValue createDefault() {
//        HashMap<String, ATSValue> map = new HashMap<String, ATSValue>();
//        for (Pair p: m_members) {
//            map.put(p.m_id, p.m_ty.createDefault());
//        }
//        return new StructValue(this, map);
//    }
    
    
    public static void update(Map<String, Object> dst, Map<String, Object> src, StructType sty) {
        for (Map.Entry<String, Object> ent: dst.entrySet()) {
            String name = ent.getKey();
            ATSType ty = sty.getMember(name);
            if (ty instanceof StructType) {
                @SuppressWarnings("unchecked")
                Map<String, Object> mdst = (Map<String, Object>)ent.getValue();
                @SuppressWarnings("unchecked")
                Map<String, Object> msrc = (Map<String, Object>)src.get(name);
                
                StructType.update(mdst, msrc, (StructType)ty);
            } else {
                dst.put(name, src.get(name));
            }
        }
        
    }

    @Override
    public Object createDefault() {
        Map<String, Object> m = new HashMap<String, Object>();
        for (Pair p: m_members) {
            m.put(p.m_id, p.m_ty.createDefault());
        }
        return m;
        
    }

}
