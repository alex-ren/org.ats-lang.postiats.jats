package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.LABint;
import jats.utfpl.stfpl.csharptype.Aux;
import jats.utfpl.stfpl.csharptype.CSClassType;
import jats.utfpl.stfpl.csharptype.CSTBookingRecord;
import jats.utfpl.stfpl.csharptype.CSTNameId;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.csharptype.ICSTypeName;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class RecType extends BoxedType {
    
    private List<ILabPat> m_labtypes;
    private int m_npf;  // Number of proofs in the pattern, if it is >= 0.
                       // (proof always appears starting from beginning.)
    private int m_knd;   // knd: 0: flat, 1: boxed, -1: unset yet
    
    public int getKind() {
        return m_knd;
    }
    
    public RecType(List<ILabPat> labtypes, int npf, int knd) {
        m_labtypes = labtypes;
        m_npf = npf;
        m_knd = knd;
    }
    
//    public RecType(List<ILabPat> labtypes, int npf) {
//        m_labtypes = labtypes;
//        m_npf = npf;
//        m_knd = -1;
//    }
    
    public boolean isBoxed() {
        return 1 == m_knd;
    }

    public boolean isTuple() {
        if (m_labtypes.isEmpty()) {
            return true;
        } else {
            if (((LabPatNorm)m_labtypes.get(0)).getLabel() instanceof LABint) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    @Override
    public RecType normalize() {
        for (ILabPat pat: m_labtypes) {
            pat.normalize();
        }
        return this;
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        RecType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return new TypeCheckResult();
        } else if (right0 instanceof RecType) {
            RecType right = (RecType)right0;
            
            if (m_labtypes.size() != right.m_labtypes.size()) {
                return new TypeCheckResult("Type mismatch: " + Log.getFilePos());
            }
            
            if (-1 == m_knd) {
                m_knd = right.m_knd;
            }
            if (-1 == right.m_knd) {
                right.m_knd = m_knd;
            }
            
            for (ILabPat pat0: m_labtypes) {
                boolean found = false;
                for (ILabPat pat1: right.m_labtypes) {
                    if (pat0.sameLab(pat1)) {
                        pat0.match(pat1);
                        found = true;
                        break;
                    }
                }
                if (false == found) {
                    return new TypeCheckResult("Type mismatch: " + Log.getFilePos());
                }
            }
            return new TypeCheckResult();
        } else {
            return new TypeCheckResult("Type mismatch: " + Log.getFilePos());
        }
    }
    

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        List<ILabPat> labpats = new ArrayList<ILabPat>();
        for (ILabPat pat: m_labtypes) {
            labpats.add(pat.instantiate(map));
        }
        return new RecType(labpats, m_npf, m_knd);
    }

    @Override
    public ISType removeProof() {
        int i = 0;
        if (m_npf > 0) {
            i = m_npf;
        }
        
        System.out.println("npf is " + i);
        
        List<ILabPat> tys = m_labtypes.subList(i, m_labtypes.size());
        if (tys.size() == 1 && isTuple()) {
            return tys.get(0).getType();
        } else {
            RecType ret = new RecType(tys, 0, m_knd);
            return ret;
        }
    }

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        List<CSClassType.LabPatNorm> cslabpats = new ArrayList<CSClassType.LabPatNorm>();
        List<Ilabel> labs = new ArrayList<Ilabel>();
        for (ILabPat labpat: m_labtypes) {
            ToCSTypeResult res = labpat.toCSType(track);
            CSClassType.LabPatNorm cslabpat = new CSClassType.LabPatNorm(((LabPatNorm)labpat).getLabel(), res.m_type);
            
            labs.add(((LabPatNorm)labpat).getLabel());
            cslabpats.add(cslabpat);
        }
        
        // find an appropriate name
        CSTBookingRecord booking = Aux.findBookingRec(track, labs);
        ICSTypeName name = null;
        if (null == booking) {
            name = CSTNameId.createTypeId("rec"); 
            booking = new CSTBookingRecord(name, labs);
            track.add(booking);  
//            throw new Error("dddddddddddddddddddddd");
        } else {
            name = booking.m_name;
        }
        
        CSClassType cstype = new CSClassType(name, cslabpats);
        return new ToCSTypeResult(cstype, null);
    }

    @Override
    public ST toSTStfpl3(STGroup stg) {
        // RecType_st(isbox, labpats) ::= <<
        ST st = stg.getInstanceOf("RecType_st");
        for (ILabPat labpat: m_labtypes) {
            st.add("labpats", labpat.toSTStfpl3(stg));
        }

        st.add("isbox", isBoxed());

        return st;
    }
    
    


//    @Override
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc) {
//        boolean is_new = false;
//        boolean is_escaped = false;
//        
//        for (ILabPat labtype: m_labtypes) {
//            NamifyResult nret = labtype.namify(map, esc);
//            if (nret.m_new) {
//                is_new = true;
//            }
//            if (nret.m_escaped) {
//                is_escaped = true;
//            }
//        }
//        
//        return Aux.namifySummary(is_escaped, is_new, this, "rec", map);
//    }
//
//    @Override
//    public boolean equalCSharp(ISType type, Map<PolyParaType, PolyParaType> env) {
//        if (type instanceof NamedType) {
//            type = ((NamedType)type).getContent();
//        }
//        
//        if (!(type instanceof RecType)) {
//            return false;
//        }
//        
//        /* compare parameters */
//        RecType rtype = (RecType)type;
//        if (m_labtypes.size() != rtype.m_labtypes.size()) {
//            return false;
//        }
//        
//        ListIterator<ILabPat> liter = m_labtypes.listIterator();
//        ListIterator<ILabPat> riter = rtype.m_labtypes.listIterator();
//        
//        while (liter.hasNext()) {
//            if (!liter.next().equalCSharp(riter.next(), env)) {
//                return false;
//            }
//        }
//
//        return true; 
//    }
    

    
    

}
