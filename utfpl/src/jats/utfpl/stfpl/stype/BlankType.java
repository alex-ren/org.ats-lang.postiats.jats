package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.AuxSType.ToCSTypeResult;

import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

/*
 * This type is used as a place holder in those scenarios in which
 * it's too difficult to specify types. If detailed type information
 * is needed, then I should specify the real type instead of using
 * this BlankType.
 */
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
