package jats.utfpl.stfpl.stype;

import jats.utfpl.stfpl.csharptype.CSIntType;
import jats.utfpl.stfpl.csharptype.ICSTypeBooking;
import jats.utfpl.stfpl.stype.AuxSType.ToCSTypeResult;

import java.util.Set;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class IntType extends EleType {
    public static IntType cInstance = new IntType();
    private IntType() {
        
    }
    @Override
    public ToCSTypeResult toCSType(Set<ICSTypeBooking> track) {
        return new ToCSTypeResult(CSIntType.c_instance, null);
    }

    @Override
    public ST toSTStfpl3(STGroup stg) {
        // IntType_st() ::= <<
        ST st = stg.getInstanceOf("IntType_st");
        if (null == st) {
            throw new Error("ererer");
        }
        return st;
    }
    
}
