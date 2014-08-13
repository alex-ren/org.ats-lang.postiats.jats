package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSIntType;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Set;

public class IntType extends EleType {
    public static IntType cInstance = new IntType();
    private IntType() {
        
    }
    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        return new ToCSTypeResult(CSIntType.c_instance, null);
    }

}
