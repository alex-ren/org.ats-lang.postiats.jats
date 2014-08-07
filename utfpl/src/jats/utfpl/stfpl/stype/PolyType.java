package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.NamedType;
import jats.utfpl.utils.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class PolyType extends BoxedType {
    public List<PolyParaType> m_paras;
    public ISType m_body;
    
    public PolyType(List<PolyParaType> paras, ISType body) {
        m_paras = paras;
        m_body = body;
    }

    @Override
    public PolyType normalize() {
        m_body = m_body.normalize();
        return this;
    }
    
    @Override
    public TypeCheckResult match(ISType ty) {
        PolyType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return new TypeCheckResult();
        } else if (right0 instanceof PolyType) {
            return new TypeCheckResult("not expecting this");
        } else {
            return new TypeCheckResult("Type mismatch: " + Log.getFilePos());
        }
    }
    
//    public FunType getNormalFunType() {
//        List<PolyParaType> paras = new ArrayList<PolyParaType>(m_paras);
//        FunType funTy = getParaFunType(paras);
//        
//        Map<PolyParaType, ISType> map = new HashMap<PolyParaType, ISType>();
//        for (PolyParaType para: paras) {
//            map.put(para, new VarType());
//        }
//        
//        ISType aType = funTy.instantiate(map);
//        
//        if (aType instanceof FunType) {
//            return (FunType)aType;
//        } else {
//            throw new Error("Check this. aType is " + aType);
//        }
//    }
    
    /*
     * Get the inner-most FunType, which consists of PolyParaType.
     */
    public FunType getParaFunType() {
        if (m_body instanceof FunType) {
            return (FunType)m_body;
        } else if (m_body instanceof PolyType) {
            return ((PolyType)m_body).getParaFunType();
        } else {
            throw new Error("Check this.");
        }
    }
    
//    /*
//     * Collect all the PloyParaType's and return the inner-most FunType.
//     */
//    private FunType getParaFunType(List<PolyParaType> paras) {
//        paras.addAll(m_paras);
//        
//        if (m_body instanceof FunType) {
//            return (FunType)m_body;
//        } else if (m_body instanceof PolyType) {
//            return ((PolyType)m_body).getParaFunType(paras);
//        } else {
//            throw new Error("Check this.");
//        }
//    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        for (PolyParaType para: map.keySet()) {
            if (m_paras.contains(para)) {
                throw new Error("This is not allowed.");
            }
        }

        ISType body = m_body.instantiate(map);
        
        return new PolyType(m_paras, body);
    }

    /*
     * Turn one level of type paras into VarType.
     */
    public ISType instantiateOne() {
        Map<PolyParaType, ISType> map = new HashMap<PolyParaType, ISType>();
        for (PolyParaType para : m_paras) {
            map.put(para, new VarType());
        }
        
        ISType aType = m_body.instantiate(map);
        return aType;
    }

//    @Override
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc) {
//        Set<PolyParaType> nesc = new HashSet<PolyParaType>();
//        NamifyResult nres = m_body.namify(map, nesc);
//        if (null != nres.m_type) {
//            m_body = nres.m_type;
//        }
//        
//        boolean is_escaped = false;
//        nesc.removeAll(m_paras);
//        if (0 < nesc.size()) {
//            is_escaped = true;
//            esc.addAll(nesc);
//        }
//
//        return Aux.namifySummary(is_escaped, nres.m_new, this, "poly", map);
//    }
//
//    @Override
//    public boolean equalCSharp(ISType type, Map<PolyParaType, PolyParaType> env) {
//        if (type instanceof NamedType) {
//        	type = ((NamedType)type).getContent();
//        }
//        if (!(type instanceof PolyType)) {
//        	return false;
//        } else {
//        	PolyType that = (PolyType)type;
//        	if (this.m_paras.size() != that.m_paras.size() || this.m_srt != that.m_srt) {
//        		return false;
//        	} else {
//        		ListIterator<PolyParaType> left = m_paras.listIterator();
//        		ListIterator<PolyParaType> right = that.m_paras.listIterator();
//        		
//        		Map<PolyParaType, PolyParaType> nenv = new HashMap<PolyParaType, PolyParaType>(env);
//        		while (left.hasNext()) {
//        			nenv.put(left.next(), right.next());
//        		}
//        		
//        		return m_body.equalCSharp(that.m_body, nenv);
//        		
//        	}
//        }
//    }
    

}
