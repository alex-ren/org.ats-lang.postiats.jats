package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

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
    

    
    

}
