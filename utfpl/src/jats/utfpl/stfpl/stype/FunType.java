package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.NamedType;
import jats.utfpl.stfpl.staexp.Ifunclo;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/*
 * This type should always appear inside UniType.
 */
public class FunType extends BoxedType {
    public int m_npf;
    public List<ISType> m_args;
    public ISType m_res;
    public Ifunclo m_funclo;
    
    public FunType(int npf, List<ISType> args, ISType res, Ifunclo funclo) {
        m_npf = npf;
        m_args = args;
        m_res = res;
        m_funclo = funclo;
    }
    
    public FunType(List<ISType> args, ISType res, Ifunclo funclo) {
        m_npf = -999;  // todo handle this for "private ISType oftype(VarType funType0, List<Id2exparg> argsLst) {"
        m_args = args;
        m_res = res;
        m_funclo = funclo;
    }

    public ISType getRetType() {
        return m_res;
    }
    
    @Override
    public FunType normalize() {
        List<ISType> tys = new ArrayList<ISType>();
        for (ISType ty: m_args) {
            ISType nty = ty.normalize();
            tys.add(nty);
        }
        
        m_args = tys;
        
        m_res = m_res.normalize();
        
        return this;
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        FunType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return new TypeCheckResult();
        } else if (right0 instanceof FunType) {
            FunType right = (FunType)right0;
            Aux.matchTypeList(left.m_args, right.m_args);
            m_res.match(right.m_res);
            
            if (-999 == m_npf) {
                m_npf = right.m_npf;
            } else {
                right.m_npf = m_npf;
            }
            
            if (null == m_funclo) {
                m_funclo = right.m_funclo;
            } else {
                right.m_funclo = m_funclo;
            }
            
            return new TypeCheckResult();
        } else {
            return new TypeCheckResult("Type mismatch: " + Log.getFilePos() + " right is " + right0);
        }
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        List<ISType> args = new ArrayList<ISType>();
        for (ISType ty: m_args) {
            ISType nty = ty.instantiate(map);
            args.add(nty);
        }
        
         ISType res = m_res.instantiate(map);
        
        return new FunType(m_npf, args, res, m_funclo);
    }

//    @Override
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc) {
//        boolean is_new = false;
//        boolean is_escaped = false;
//
//        ListIterator<ISType> iter = m_args.listIterator();
//        while (iter.hasNext()) {
//            ISType type = iter.next();
//            NamifyResult res = type.namify(map, esc);
//            if (res.m_escaped) {
//                is_escaped = true;
//            } else {
//                if (true == res.m_new) {
//                    is_new = true;
//                }
//                if (null != res.m_type) {
//                    iter.set(res.m_type);
//                }
//            }
//        }
//        
//        NamifyResult res = m_res.namify(map, esc);
//        if (res.m_escaped) {
//            is_escaped = true;
//        } else {
//            if (true == res.m_new) {
//                is_new = true;
//            }
//            if (null != res.m_type) {
//                m_res = res.m_type;
//            }
//        }
//
//        return Aux.namifySummary(is_escaped, is_new, this, "fun", map);
//
//    }
//
//    @Override
//    public boolean equalCSharp(ISType type, Map<PolyParaType, PolyParaType> env) {
//        if (type instanceof NamedType) {
//            type = ((NamedType)type).getContent();
//        }
//        
//        if (!(type instanceof FunType)) {
//            return false;
//        }
//        
//        /* compare parameters */
//        FunType ftype = (FunType)type;
//        if (m_args.size() != ftype.m_args.size()) {
//            return false;
//        }
//        
//        ListIterator<ISType> liter = m_args.listIterator();
//        ListIterator<ISType> riter = ftype.m_args.listIterator();
//        
//        while (liter.hasNext()) {
//            if (!liter.next().equalCSharp(riter.next(), env)) {
//                return false;
//            }
//        }
//        
//        /* compare return type */
//        if (!m_res.equalCSharp(ftype.m_res, env)) {
//            return false;
//        }
//        
//        return true; 
//        
//    }
    
}




    
    
    
    
