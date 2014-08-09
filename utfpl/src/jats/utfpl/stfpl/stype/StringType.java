package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSStringType;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Set;

public class StringType extends EleType {
    public static StringType cInstance = new StringType();
    private StringType() {
        
    }
    
    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        return new ToCSTypeResult(CSStringType.c_instance, null);
    }

}
