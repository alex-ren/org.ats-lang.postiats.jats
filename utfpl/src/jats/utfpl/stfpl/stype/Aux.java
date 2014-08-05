package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.staexp.Ifunclo;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class Aux {
    public static void matchTypeList(List<ISType> left, List<ISType> right) {
        if (left.size() != right.size()) {
            throw new Error("Type mismatch.");
        }
        
        ListIterator<ISType> iter0 = left.listIterator();
        ListIterator<ISType> iter1 = right.listIterator();
        
        while (iter0.hasNext()) {
            iter0.next().match(iter1.next());
        }
    }
    
    public static Ifunclo getClosureInfo(ISType type) {
        if (type instanceof FunType) {
            return ((FunType)type).m_funclo;
        } else if (type instanceof PolyType) {
            return getClosureInfo(((PolyType)type).m_body);
        } else {
            throw new Error(type + " is not supported.");
        }
    }
    
    public static ISType getRetType(ISType type) {
        while (true) {
            if (type instanceof FunType) {
                return ((FunType)type).getRetType();
            } else if (type instanceof PolyType) {
                type = ((PolyType)type).m_body;
            } else {
                throw new Error("unexcepted type " + type);
            }
        }
    
    }
    
    public static ITypeName findType(Map<ITypeName, NamedType> map, ISType type) {
        Set<Map.Entry<ITypeName, NamedType>> entry_set = map.entrySet();
        for (Map.Entry<ITypeName, NamedType> entry: entry_set) {
            if (entry.getValue().getContent().equalCSharp(type)) {
                return entry.getKey();
            } else {
                return null;
            }
        }
    }

}
