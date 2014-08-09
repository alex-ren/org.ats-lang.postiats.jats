package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSFloatType;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Set;

public class IntType extends EleType {
    public static IntType cInstance = new IntType();
    private IntType() {
        
    }
    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        return new ToCSTypeResult(CSFloatType.c_instance, null);
    }

}
