package jats.utfpl.utfpl.stype;

/*
 * SType: Simple Type
 */
public interface ISType {
//    boolean equals(ISType ty);
    
//    public Object accept(STypeVisitor visitor);
    
    // This operation may change the content of the object.
    public ISType normalize();  // Can be VarType
    
    // This operation cannot change the content of the object.
    public ISType instantiate(PolyParaType para, ISType arg);
    
    public void match(ISType ty);

}
