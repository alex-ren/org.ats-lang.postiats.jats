package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.AuxSType.ToCSTypeResult;
import jats.utfpl.utils.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

// refer to DefaultAppTypeStore.java
public class AppType extends BoxedType {
    private String m_con;
    private List<ISType> m_tys;
    
    public AppType(String con, List<ISType> tys) {
        if (tys == null) {
            throw new Error("Not allowed");
        }
        m_con = con;
        m_tys = tys;
        throw new Error("not in use now");
    }

    @Override
    public AppType normalize() {
        List<ISType> tys = new ArrayList<ISType>();
        for (ISType ty: m_tys) {
            ISType nty = ty.normalize();
            tys.add(nty);
        }
        
        m_tys = tys;
        return this;
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        AppType left = this.normalize();
        ISType right0 = ty.normalize();
        
        if (right0 instanceof VarType) {
            ((VarType)right0).setType(left);
            return new TypeCheckResult();
        } else if (right0 instanceof AppType) {
            AppType right = (AppType)right0;
            if (!left.m_con.equals(right.m_con)) {
                return new TypeCheckResult("type mismatch: " + Log.getFilePos());
            }
            
            if (left.m_tys.size() != right.m_tys.size()) {
                return new TypeCheckResult("type mismatch: " + Log.getFilePos());
            }
            
            ListIterator<ISType> iter0 = left.m_tys.listIterator();
            ListIterator<ISType> iter1 = right.m_tys.listIterator();
            while (iter0.hasNext()) {
                iter0.next().match(iter1.next());
            }
            return new TypeCheckResult();
        } else {
            return new TypeCheckResult("type mismatch: " + Log.getFilePos());
        }
        
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        List<ISType> tys = new ArrayList<ISType>();
        for (ISType ty: m_tys) {
            ISType nty = ty.instantiate(map);
            tys.add(nty);
        }
        
        return new AppType(m_con, tys);
    }

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        throw new Error("This should not happen.");
    }

    @Override
    public ST toSTStfpl3(STGroup stg) {
        throw new Error("This should not happen.");
    }

    @Override
    public ISType removeProof() {
        throw new Error("This should not happen.");
    }


//    @Override
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc) {
//        throw new Error("not supported yet");
//    }
//
//    @Override
//    public boolean equalCSharp(ISType type, Map<PolyParaType, PolyParaType> env) {
//        throw new Error("not supported yet");
//    }

}







