package jats.utfpl.stfpl.stype;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Map;
import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

/*
 * SType: Simple Type
 */
public interface ISType {
    
    public boolean equals(Object right);
    
    /* ************ *********** */
    
    // This operation may change the content of the object.
    public ISType normalize();  // Can be VarType
    
    // This operation cannot change the content of the object.
    public ISType instantiate(Map<PolyParaType, ISType> map);
    
    public TypeCheckResult match(ISType ty);
    
    /* ************ *********** */
    
    // create a new type without any proof
    // used in creating dynexp3
    public ISType removeProof();
    
    // For print at layer of dynexp3
    public ST toSTStfpl3(STGroup stg);

    /* ************ *********** */
    
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track);

}


