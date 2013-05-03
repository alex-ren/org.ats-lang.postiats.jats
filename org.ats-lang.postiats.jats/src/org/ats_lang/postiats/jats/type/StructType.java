package org.ats_lang.postiats.jats.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.lang.model.type.PrimitiveType;

public class StructType extends ATSKindType {
    private List<Pair> m_members;
    private String m_name; // This name is for generating code.

    public StructType(String name) {
        super(Decorator.TYPE);
        m_members = new ArrayList<Pair>();
        m_name = name;
    }

    public void addMember(String id, ATSType ty) {
        m_members.add(new Pair(id, ty));
    }

    public ATSType getMember(String id) {
        for (Pair mem : m_members) {
            if (mem.m_id.equals(id)) {
                return mem.m_ty;
            }
        }
        throw new Error("member not found");
    }

    // @Override
    // public String toString() {
    // return m_name;
    // }

    @Override
    public int getSize() {
        int accu = 0;
        for (Pair p : m_members) {
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

        boolean equals(Pair p) {
            if (m_id.equals(p.m_id) && m_ty.equals(p.m_ty)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void update(Map<String, Object> dst, Map<String, Object> src,
            StructType sty) {
        for (Map.Entry<String, Object> ent : dst.entrySet()) {
            String name = ent.getKey();
            ATSType ty = sty.getMember(name);
            if (ty instanceof StructType) {
                @SuppressWarnings("unchecked")
                Map<String, Object> mdst = (Map<String, Object>) ent.getValue();
                @SuppressWarnings("unchecked")
                Map<String, Object> msrc = (Map<String, Object>) src.get(name);

                StructType.update(mdst, msrc, (StructType) ty);
            } else {
                dst.put(name, src.get(name));
            }
        }
    }

    @Override
    public Map<String, Object> createNormalDefault() {
        return new HashMap<String, Object>();
    }

    @Override
    public Object createRefDefault() {
        Map<String, Object> m = new HashMap<String, Object>();
        for (Pair p : m_members) {
            if (p.m_ty instanceof ATSPrimType) {
                m.put(p.m_id, p.m_ty.createNormalDefault());
            } else if (p.m_ty instanceof StructType) {
                m.put(p.m_id, p.m_ty.createRefDefault());
            } else {
                System.out.println("createRefDefault p.m_ty is " + p.m_ty);
                throw new Error("not supported");
            }

        }
        return m;
    }

    static public Map<String, Object> cloneValue(Map<String, Object> src,
            StructType sty) {
        Map<String, Object> dst = new HashMap<String, Object>();
        for (Map.Entry<String, Object> ent : src.entrySet()) {
            String name = ent.getKey();
            ATSType ty = sty.getMember(name);
            if (ty instanceof StructType) {
                @SuppressWarnings("unchecked")
                Map<String, Object> innermap = (Map<String, Object>) ent
                        .getValue();
                dst.put(name, StructType.cloneValue(innermap, (StructType) ty));
            } else {
                dst.put(name, src.get(name));
            }
        }
        return dst;
    }

    @Override
    public boolean equals(ATSType ty) {
        if (ty instanceof StructType) {
            List<Pair> right = ((StructType) ty).m_members;
            if (m_members.size() != right.size()) {
                return false;
            }
            Iterator<Pair> iterleft = m_members.iterator();
            Iterator<Pair> iterright = right.iterator();
            while (iterleft.hasNext()) {
                Pair pairleft = iterleft.next();
                Pair pairright = iterright.next();
                if (!pairleft.equals(pairright)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

}
