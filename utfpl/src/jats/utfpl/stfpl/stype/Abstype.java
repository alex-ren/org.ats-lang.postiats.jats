package jats.utfpl.stfpl.stype;

import java.util.Map;

public class Abstype extends SortType {
//    private ITypeName m_name;

    public Abstype(ESort srt/*, ITypeName name*/) {
        super(srt);
//        m_name = name;
    }
    
//    public ITypeName getName() {
//        return m_name;
//    }

    @Override
    public ISType normalize() {
        throw new Error("check this");
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        throw new Error("check this");
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        throw new Error("check this");
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


