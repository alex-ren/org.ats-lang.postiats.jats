package jats.utfpl.stfpl.stype;

import java.util.Map;

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

}
