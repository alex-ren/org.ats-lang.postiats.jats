package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.csharptype.NamedType;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class RecType extends BoxedType {
    
    private List<ILabPat> m_labtypes;
    private int m_npf;  // Number of proofs in the pattern, if it is >= 0.
                       // (proof always appears starting from beginning.)
    private int m_knd;  // 0: flat, 1: boxed, -1: uninitialized
    
    public int getKind() {
        return m_knd;
    }
    
    public RecType(List<ILabPat> labtypes, int npf, int knd) {
        m_labtypes = labtypes;
        m_npf = npf;
        m_knd = knd;
    }
    
    public RecType(List<ILabPat> labtypes, int npf) {
        m_labtypes = labtypes;
        m_npf = npf;
        m_knd = -1;
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
    
    static public interface ILabPat {
        public void normalize();

        public boolean sameLab(ILabPat pat1);

        public void match(ILabPat next);

        public ILabPat instantiate(Map<PolyParaType, ISType> map);
        
//        public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc);
//
//        public boolean equalCSharp(ILabPat next,
//                Map<PolyParaType, PolyParaType> env);
        
    }
    
    static public class LabPatNorm implements ILabPat {
        private Ilabel m_lab;
        private ISType m_type;
        
        public LabPatNorm(Ilabel lab, ISType type) {
            m_lab = lab;
            m_type = type;
        }

        @Override
        public void normalize() {
            m_type.normalize();
        }

        @Override
        public boolean sameLab(ILabPat pat1) {
            if (pat1 instanceof LabPatNorm) {
                LabPatNorm lab = (LabPatNorm)pat1;
                return m_lab.equals(lab.m_lab);
            } else {
                return false;
            }
        }

        @Override
        public void match(ILabPat right0) {
            if (right0 instanceof LabPatNorm) {
                LabPatNorm right = (LabPatNorm)right0;
                m_type.match(right.m_type);
            } else {
                throw new Error("This shall not happen.");
            }
        }

        @Override
        public ILabPat instantiate(Map<PolyParaType, ISType> map) {
            ISType type = m_type.instantiate(map);
            
            return new LabPatNorm(m_lab, type);
            
        }

//        @Override
//        public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc) {
//            NamifyResult nres = m_type.namify(map, esc);
//            if (null != nres.m_type) {
//                m_type = nres.m_type;
//            }
//            return nres;
//            
//        }
//
//        @Override
//        public boolean equalCSharp(ILabPat rlab,
//                Map<PolyParaType, PolyParaType> env) {
//            if (rlab instanceof LabPatNorm) {
//                LabPatNorm right = (LabPatNorm)rlab;
//                
//                if (!m_lab.equals(right.m_lab)) {
//                    return false;
//                } else {
//                    return m_type.equalCSharp(right.m_type, env);
//                }
//            } else {
//                throw new Error("not supported");
//            }
//        }

    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        List<ILabPat> labpats = new ArrayList<ILabPat>();
        for (ILabPat pat: m_labtypes) {
            labpats.add(pat.instantiate(map));
        }
        return new RecType(labpats, m_npf, m_knd);
    }

    public RecType removeProof() {
        List<ILabPat> tys = new ArrayList<ILabPat>();
        int i = 0;
        if (m_npf > 0) {
            i = m_npf;
        }
        ListIterator<ILabPat> iter = m_labtypes.listIterator(i);
        while (iter.hasNext()) {
            tys.add(iter.next());
        }
        RecType ret = new RecType(tys, 0);
        return ret;
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
