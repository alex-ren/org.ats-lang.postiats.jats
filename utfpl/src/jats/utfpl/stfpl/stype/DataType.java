package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSPolyParaType;
import jats.utfpl.stfpl.csharptype.ICSType;
import jats.utfpl.stfpl.csharptype.ICSTypeName;
import jats.utfpl.stfpl.staexp.Cs2cst;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class DataType extends BoxedType {
    public List<ISType> m_tyLst;
    public Cs2cst m_name;
    
    public DataType(Cs2cst name, List<ISType> tyLst) {
        m_tyLst = tyLst;
        m_name = name;
    }

    @Override
    public ISType normalize() {
        ListIterator<ISType> iter = m_tyLst.listIterator();
        if (iter.hasNext()) {
            ISType ty = iter.next().normalize();
            iter.set(ty);
        }
        return this;
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        return this;
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        this.normalize();
        ISType right = ty.normalize();
        if (right instanceof VarType) {
            ((VarType) right).setType(this);
            return new TypeCheckResult();
        } else if (right instanceof DataType) {
            DataType right1 = (DataType)right;
            if (m_name != right1.m_name) {
                throw new Error("type mismatch");
            }
            for (int i = 0; i < m_tyLst.size(); ++i) {
                m_tyLst.get(i).match(right1.m_tyLst.get(i));
            }
            return new TypeCheckResult();
        } else {
            return new TypeCheckResult("not expecting " + ty);
        }
    }

    @Override
    public ToCSTypeResult toCSType(Map<ICSTypeName, ICSType> map,
            Set<CSPolyParaType> esc) {
        throw new Error("not supported yet");
    }

//    @Override
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc) {
//        throw new Error("not supported");
//    }
//
//    @Override
//    public boolean equalCSharp(ISType type, Map<PolyParaType, PolyParaType> env) {
//        throw new Error("not supported");
//    }

}


