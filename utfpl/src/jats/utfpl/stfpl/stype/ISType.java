package jats.utfpl.stfpl.stype;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

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
    
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track);
    
    
//    /*
//     * If the function returns null, then use the original type 
//     * instead of the type inside the NamifyResult.
//     * After Namify, all nonnamed types are updated by named types.
//     */
//    /*
//     * Caution: This method may modify the content of the object.
//     */
//    public NamifyResult namify(Map<ITypeName, NamedType> map, Set<PolyParaType> esc);
    
    
//    public NamifyResult namify2(Map<ITypeName, NamedType> map, Set<PolyParaType> esc);

//    // Both current and the input type have to be closed.
//    // env is for mapping of Type parameters.
//    public boolean equalCSharp(ISType type, Map<PolyParaType, PolyParaType> env);
    
//    static public class NamifyResult {
//        public ICSType m_type;
//        
//        public Boolean m_new;  // is newly created or already in the map
//                               // can be null, which indicates invalid.
//                               // (m_new == true) => (m_type != null)
//                               // not correct on the other direction.
//                               
////        public boolean m_escaped;  // is this type closed
////                                   // (m_escaped == true) => (m_new == null)
//        
//        public NamifyResult(NamedType type, Boolean _new/*, boolean escaped*/) {
//            m_type = type;
//            m_new = _new;
////            m_escaped = escaped;
//        }
//    }
    
}
