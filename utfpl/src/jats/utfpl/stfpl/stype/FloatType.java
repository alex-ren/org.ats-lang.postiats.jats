package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSFloatType;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Set;

public class FloatType extends EleType {
    public static FloatType cInstance = new FloatType();
    private FloatType() {
        
    }

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        return new ToCSTypeResult(CSFloatType.c_instance, null);
    }

}
