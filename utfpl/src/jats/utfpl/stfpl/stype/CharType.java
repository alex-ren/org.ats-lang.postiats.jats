package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSCharType;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.Aux.ToCSTypeResult;

import java.util.Set;

public class CharType extends EleType {
    public static CharType cInstance = new CharType();
    private CharType() {
        
    }

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        return new ToCSTypeResult(CSCharType.c_instance, null);
    }

}
