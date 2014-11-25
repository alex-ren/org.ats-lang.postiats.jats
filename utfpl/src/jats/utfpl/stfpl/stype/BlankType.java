package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.AuxSType.ToCSTypeResult;

import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class BlankType extends EleType {
    public static BlankType cInstance = new BlankType();
    private BlankType() {
        
    }

    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        throw new Error("Should not happen.");
    }
    
    @Override
    public ST toSTStfpl3(STGroup stg) {
    	throw new Error("Should not happen.");
    }
}
