package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.LABint;
import jats.utfpl.stfpl.csharptype.Aux;
import jats.utfpl.stfpl.csharptype.CSClassType;
import jats.utfpl.stfpl.csharptype.CSTBookingRecord;
import jats.utfpl.stfpl.csharptype.CSTNameId;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.csharptype.ICSTypeName;
import jats.utfpl.stfpl.stype.AuxSType.ToCSTypeResult;
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

    /*
     * Check this type is tuple not record.
     */
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
        
        List<ILabPat> tys = new ArrayList<ILabPat>();
        for (ILabPat ty: m_labtypes) {
        	--i;
        	if (i >= 0 && ty.isProof()) {
        		continue;
        	}
        	
        	if ((ty instanceof VoidType) && tys.size() > 0) {
        		// turn the case of (t1, t2, | void) into (t1, t2)
        		break;
        	}
        	
        	tys.add(ty);
        }
 
//        List<ILabPat> tys = m_labtypes.subList(i, m_labtypes.size());
        // flat tuple with one element
        if (tys.size() == 1 && isTuple() && !isBoxed()) {
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
    
    
    

}
