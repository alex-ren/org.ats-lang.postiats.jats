package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.staexp.Cs2cst;
import jats.utfpl.stfpl.stype.AuxSType.ToCSTypeResult;
import jats.utfpl.utils.Log;

import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

// abstype mutex_t
public class Abstype extends SortType {
//    private ITypeName m_name;
    public Cs2cst m_name;

    public Abstype(Cs2cst name, ESort srt/*, ITypeName name*/) {
        super(srt);
        m_name = name;
    }
    
//    public ITypeName getName() {
//        return m_name;
//    }

    @Override
    public ISType normalize() {
    	return this;
//        throw new Error("check this id is " + m_name.toStringNoStamp());
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        return this;
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(this);
            return new TypeCheckResult();
        } else if (right0 instanceof Abstype) {
            if (right0 != this && m_name != ((Abstype)right0).m_name) {
                return new TypeCheckResult("Type mismatch: " +
                  Log.getFilePos() + " 01. left is " + this + ", right is " + right0);
            } else {
                return new TypeCheckResult();
            }
        } else {
            return new TypeCheckResult("Type mismatch: " + 
                    Log.getFilePos() + " 01. left is " + this + ", right is " + right0);
        }
    }

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        throw new Error("not supported");
    }

    @Override
    public ST toSTStfpl3(STGroup stg) {
        // AbsType_st(cst_name) ::= <<
        ST st = stg.getInstanceOf("AbsType_st");
        st.add("cst_name", m_name);
        return st;
    }

    @Override
    public ISType removeProof() {
        return new Abstype(m_name, m_srt);
    }


//    @Override
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc) {
//        boolean is_new = false;
//        NamedType nty = map.get(m_name);
//        if (null == nty) {
//            nty = new NamedType(this, m_name);
//            map.put(m_name, nty);
//            is_new = true;
//        }
//        
//        NamifyResult ret = new NamifyResult(nty, is_new, false);
//        return ret;
//    }
//
//    @Override
//    public boolean equalCSharp(ISType type, Map<PolyParaType, PolyParaType> env) {
//        if (type instanceof NamedType) {
//            type = ((NamedType)type).getContent();            
//        }
//        if (!(type instanceof Abstype)) {
//            Abstype right = (Abstype)type;
//            return m_name.equals(right.m_name);
//        } else {
//            return false;
//        }
//    }

}


