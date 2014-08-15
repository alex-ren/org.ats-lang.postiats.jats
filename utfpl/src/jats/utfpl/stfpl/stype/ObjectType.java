package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSObjectType;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Map;
import java.util.Set;

public class ObjectType extends SortType {
    
    public static ObjectType c_instance = new ObjectType();

    private ObjectType() {
        super(ESort.type);
    }

    @Override
    public ISType normalize() {
        throw new Error("Should not happen");
    }

    @Override
    public ISType instantiate(Map<PolyParaType, ISType> map) {
        throw new Error("Should not happen");
    }

    @Override
    public TypeCheckResult match(ISType ty) {
        throw new Error("Should not happen");
    }

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        return new ToCSTypeResult(CSObjectType.c_instance, null);
    }

}
