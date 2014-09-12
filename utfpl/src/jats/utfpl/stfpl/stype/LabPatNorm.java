package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.Ilabel;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;


public class LabPatNorm implements ILabPat {
    private Ilabel m_lab;
    private ISType m_type;
    
    public LabPatNorm(Ilabel lab, ISType type) {
        m_lab = lab;
        m_type = type;
    }

    public Ilabel getLabel() {
        return m_lab;
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

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        return m_type.toCSType(track);
    }
    
    public ST toSTStfpl3(STGroup stg) {
        ST st = stg.getInstanceOf("labpat_st");
        // labpat_st(lab, type) ::= <<
        st.add("lab", m_lab);
        st.add("type", m_type.toSTStfpl3(stg));
        return st;
    }

    @Override
    public ISType getType() {
        return m_type;
    }

//    @Override
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc) {
//        NamifyResult nres = m_type.namify(map, esc);
//        if (null != nres.m_type) {
//            m_type = nres.m_type;
//        }
//        return nres;
//        
//    }
//
//    @Override
//    public boolean equalCSharp(ILabPat rlab,
//            Map<PolyParaType, PolyParaType> env) {
//        if (rlab instanceof LabPatNorm) {
//            LabPatNorm right = (LabPatNorm)rlab;
//            
//            if (!m_lab.equals(right.m_lab)) {
//                return false;
//            } else {
//                return m_type.equalCSharp(right.m_type, env);
//            }
//        } else {
//            throw new Error("not supported");
//        }
//    }

}