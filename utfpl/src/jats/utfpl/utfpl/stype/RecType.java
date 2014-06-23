package jats.utfpl.utfpl.stype;

import jats.utfpl.utfpl.dynexp.Ilabel;

import java.util.ArrayList;
import java.util.List;

public class RecType extends BoxedType {
    
    private List<ILabPat> m_labtypes;
    private int m_npf;  // Number of proofs in the pattern, if it is >= 0.
                       // (proof always appears starting from beginning.)
    
    public RecType(List<ILabPat> labtypes, int npf) {
        m_labtypes = labtypes;
        m_npf = npf;
    }

    @Override
    public RecType normalize() {
        for (ILabPat pat: m_labtypes) {
            pat.normalize();
        }
        return this;
    }

    @Override
    public RecType instantiate(PolyParaType para, ISType arg) {
        List<ILabPat> labpats = new ArrayList<ILabPat>();
        for (ILabPat pat: m_labtypes) {
            labpats.add(pat.instantiate(para, arg));
        }
        return new RecType(labpats, m_npf);
    }

    @Override
    public void match(ISType ty) {
        RecType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return;
        } else if (right0 instanceof RecType) {
            RecType right = (RecType)right0;
            
            if (m_labtypes.size() != right.m_labtypes.size()) {
                throw new Error("Type mismatch.");
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
                    throw new Error("Type mismatch.");
                }
            }
        } else {
            throw new Error("Type mismatch.");
        }
    }
    
    static public interface ILabPat {
        public void normalize();

        public boolean sameLab(ILabPat pat1);

        public void match(ILabPat next);

        public ILabPat instantiate(PolyParaType para, ISType arg);
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
        public ILabPat instantiate(PolyParaType para, ISType arg) {
            ISType type = m_type.instantiate(para, arg);
            
            return new LabPatNorm(m_lab, type);
            
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
    }
    

    
    

}
