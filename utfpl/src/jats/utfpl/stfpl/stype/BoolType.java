package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSBoolType;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Set;

public class BoolType extends EleType {
    public static BoolType cInstance = new BoolType();
    private BoolType() {
        
    }

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        return new ToCSTypeResult(CSBoolType.c_instance, null);
    }

}
