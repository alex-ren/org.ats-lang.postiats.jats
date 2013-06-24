package org.ats_lang.postiats.jats.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.ats_lang.postiats.jats.value.Ptrk.Location;

public class StructType extends ATSReferableType {
    private List<Pair> m_members;
    Map<String, ATSReferableType> m_typemap;
    private String m_name; // This name is for generating code.

    public StructType(String name) {
        super(Decorator.T0YPE);
        m_members = new ArrayList<Pair>();
        m_name = name;
        m_typemap = new HashMap<String, ATSReferableType>();
    }

    @Override
    public Map<String, Object> createNormalDefault() {
    	// a little bit waste if this is not for creating reference
        Map<String, Object> m = new HashMap<String, Object>();
        for (Pair p : m_members) {
            if (p.m_ty instanceof ATSReferableType) {
                m.put(p.m_id, p.m_ty.createNormalDefault());
            } else {
                System.out.println("createNormalDefault p.m_ty is " + p.m_ty);
                throw new Error("not supported");
            }
        }
        return m;
    }

    @Override
    public boolean equals(ATSType ty) {
    	if (this == ty) {
    		return true;
    	}
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
    
    public void addMember(String id, ATSReferableType ty) {
        m_members.add(new Pair(id, ty));
        m_typemap.put(id, ty);
    }
    
    public int calcOffset(String id) {
    	int offset = 0;
    	for (Pair pt: m_members) {
    		if (pt.m_id.equals(id)) {
    			break;
    		}
    		offset += pt.m_ty.getSize();
    	}
    	return offset;
    }

    public ATSReferableType getMember(String id) {
        return m_typemap.get(id);
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
        ATSReferableType m_ty;

        public Pair(String id, ATSReferableType ty) {
            m_id = id;
            m_ty = ty;
        }
        
//        String getId() {
//        	return m_id;
//        }
//        
//        ATSReferableType getType() {
//        	return m_ty;
//        }

        boolean equals(Pair p) {
            if (m_id.equals(p.m_id) && m_ty.equals(p.m_ty)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public Map<String, Object> cloneValue(Object src) {
    	@SuppressWarnings("unchecked")
        Map<String, Object> structsrc = (Map<String, Object>) src;
    	
        Map<String, Object> dst = new HashMap<String, Object>();
        for (Map.Entry<String, Object> ent : structsrc.entrySet()) {
            String name = ent.getKey();
            ATSReferableType ty = m_typemap.get(name);
            dst.put(name, ty.cloneValue(ent.getValue()));
        }
        return dst;
    }
    
    public void copyFrom(Object dst, Object src) {
    	@SuppressWarnings("unchecked")
        Map<String, Object> ssrc = (Map<String, Object>) src;
    	@SuppressWarnings("unchecked")
        Map<String, Object> sdst = (Map<String, Object>) dst;
        for (Map.Entry<String, Object> ent : ssrc.entrySet()) {
            String name = ent.getKey();
            ATSReferableType memty = m_typemap.get(name);
			if (memty instanceof ATSEltType) {
				sdst.put(name, ssrc.get(name));
			} else if (memty instanceof StringType) {
				((StringType)memty).copyFrom(sdst.get(name), ent.getValue());
			} else if (memty instanceof ArrayType) {
				((ArrayType)memty).copyFrom(sdst.get(name), ent.getValue());		
			} else if (memty instanceof StructType) {
				((StructType)memty).copyFrom(sdst.get(name), ent.getValue());
			} else {
				throw new Error("type mismatch");
			}
        }
    	
    }

	class StructMemberLocation implements Location {
		Map<String, Object> m_content;
		String m_name;
		ATSReferableType m_elety;
		
		public StructMemberLocation(Map<String, Object> content, String name, ATSReferableType elety) {
	        m_content = content;
	        m_name = name;
	        m_elety = elety;
        }

		@Override
		public void update(Object src) {
			if (m_elety instanceof ATSEltType) {
				m_content.put(m_name, src);
			} else if (m_elety instanceof StringType) {
				((StringType)m_elety).copyFrom(m_content.get(m_name), src);
			} else if (m_elety instanceof ArrayType) {
				((ArrayType)m_elety).copyFrom(m_content.get(m_name), src);				
			} else if (m_elety instanceof StructType) {
				((StructType)m_elety).copyFrom(m_content.get(m_name), src);
			} else {
				throw new Error("type mismatch");
			}
		}

		@Override
		public Object getValue() {
			return m_content.get(m_name);
		}

	}
    // m_elety may be equal to loctype
	public Location getLoc(Object content, int offset, ATSReferableType loctype) {
		@SuppressWarnings("unchecked")
        Map<String, Object> structsrc = (Map<String, Object>) content;
		Pair curp = null;
		for (Pair p: m_members) {
			curp = p;
			if (offset < p.m_ty.getSize()) {
				break;
			}
			offset -= p.m_ty.getSize();
		}
		
		ATSReferableType curtype = curp.m_ty;
		String curname = curp.m_id;
		
		if (curtype.equals(loctype)) {
			return new StructMemberLocation(structsrc, curname, curtype);
		}
		
		if (curtype instanceof ATSEltType) {
		    System.out.println("eeeeeeeeeeeeeeeeeeeeeeeecurtype is " + curtype);
			throw new Error("type mismatch");
		} else if (curtype instanceof StringType) {
				return ((StringType) curtype).getLoc(structsrc.get(curname), offset);
		} else if (curtype instanceof ArrayType) {
				return ((ArrayType) curtype).getLoc(structsrc.get(curname), offset, loctype);
		} else if (curtype instanceof StructType) {
				return ((StructType) curtype).getLoc(structsrc.get(curname), offset, loctype);
		} else {
			throw new Error("Type mismatch");
		}
	}


}
