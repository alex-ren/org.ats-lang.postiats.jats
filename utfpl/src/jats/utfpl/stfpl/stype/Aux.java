package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.NamedType;
import jats.utfpl.stfpl.staexp.Ifunclo;
import jats.utfpl.stfpl.stype.ISType.NamifyResult;

import java.util.HashMap;
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
//    
//    public static NamedType findType(Map<ITypeName, NamedType> map, ISType type) {
//        Set<Map.Entry<ITypeName, NamedType>> entry_set = map.entrySet();
//        for (Map.Entry<ITypeName, NamedType> entry: entry_set) {
//            Map<PolyParaType, PolyParaType> env = new HashMap<PolyParaType, PolyParaType>();
//            if (entry.getValue().equalCSharp(type, env)) {
//                // found
//                return entry.getValue();
//            }
//        }
//        
//        return null;  // not found
//    }
//
//    public static NamifyResult namifySummary(boolean is_escaped,
//            boolean is_new, SortType type, String name,
//            Map<ITypeName, NamedType> map) {
//        if (is_escaped) {
//            return new NamifyResult(null, null, true);
//        } else {
//            if (is_new) {
//                TNameId tid = TNameId.createTypeId(name);
//                NamedType named_type = new NamedType(type, tid);
//                map.put(tid, named_type);
//                NamifyResult ret = new NamifyResult(named_type, true, false);
//                return ret;
//            } else {
//                NamedType named_type = Aux.findType(map, type);
//                if (null == named_type) {
//                    TNameId tid = TNameId.createTypeId(name);
//                    named_type = new NamedType(type, tid);
//                    map.put(tid, named_type);
//                    NamifyResult ret = new NamifyResult(named_type, true, false);
//                    return ret;
//                } else {
//                    NamifyResult ret = new NamifyResult(named_type, false, false);
//                    return ret;
//                }
//            }
//        }
//    }


}
