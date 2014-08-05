package jats.utfpl.stfpl.stype;

import java.util.Map;
import java.util.Set;

/*
 * SType: Simple Type
 */
public interface ISType {
//    boolean equals(ISType ty);
    
//    public Object accept(STypeVisitor visitor);
    
    // This operation may change the content of the object.
    public ISType normalize();  // Can be VarType
    
    // This operation cannot change the content of the object.
    public ISType instantiate(Map<PolyParaType, ISType> map);
    
    public TypeCheckResult match(ISType ty);
    
    public boolean equals(Object right);
    
    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> env);

//    public boolean containPolyType();
    

    public boolean equalCSharp(ISType type);
    
    static public class NamifyResult {
        public NamedType m_type;
        public boolean m_new;  // is newly created or already in the map
        public boolean m_escaped;
        
        public NamifyResult(NamedType type, boolean _new, boolean escaped) {
            m_type = type;
            m_new = _new;
            m_escaped = escaped;
        }
    }
    
}
